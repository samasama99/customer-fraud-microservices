package org.samasama.customer;

import org.samasama.amqp.RabbitMQMessageProducer;
import org.samasama.clients.email_validation.EmailValidationClient;
import org.samasama.clients.email_validation.EmailValidationResponse;
import org.samasama.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public record CustomerService(
        CustomerRepository customerRepository,
        EmailValidationClient emailValidationClient,
        RabbitMQMessageProducer rabbitMQMessageProducer) {

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer =
                Customer.builder()
                        .firstName(customerRegistrationRequest.firstName())
                        .lastName(customerRegistrationRequest.lastName())
                        .email(customerRegistrationRequest.email())
                        .build();
        String encodedEmail = URLEncoder.encode(customer.getEmail(), StandardCharsets.UTF_8);
        EmailValidationResponse emailValidationResponse = emailValidationClient.isValid(encodedEmail);
        assert emailValidationResponse != null;
        if (!emailValidationResponse.isValid()) {
            throw new IllegalStateException();
        }
        customerRepository.saveAndFlush(customer);
        NotificationRequest notificationRequest =
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, welcome to samasama !", customer.getFirstName()));
        rabbitMQMessageProducer.publish(
                notificationRequest, "internal.exchange", "internal.notification.routing-key");
    }
}
