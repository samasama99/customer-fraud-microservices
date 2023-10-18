package org.samasama.customer;

import org.samasama.amqp.RabbitMQMessageProducer;
import org.samasama.clients.fraud.FraudCheckResponse;
import org.samasama.clients.fraud.FraudClient;
import org.samasama.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(
    CustomerRepository customerRepository,
    FraudClient fraudClient,
    RabbitMQMessageProducer rabbitMQMessageProducer) {

  public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
    Customer customer =
        Customer.builder()
            .firstName(customerRegistrationRequest.firstName())
            .lastName(customerRegistrationRequest.lastName())
            .email(customerRegistrationRequest.email())
            .build();
    customerRepository.saveAndFlush(customer);
    FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
    assert fraudCheckResponse != null;
    if (fraudCheckResponse.isFraudster()) {
      throw new IllegalStateException();
    }
    NotificationRequest notificationRequest =
        new NotificationRequest(
            customer.getId(),
            customer.getEmail(),
            String.format("Hi %s, welcome to samasama !", customer.getFirstName()));
    rabbitMQMessageProducer.publish(
        notificationRequest, "internal.exchange", "internal.notification.routing-key");
  }
}
