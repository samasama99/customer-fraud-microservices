package com.samasama.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate ) {

  public void registerCustomer(
      CustomerRegistrationRequest customerRegistrationRequest) {
    Customer customer =
        Customer.builder()
            .firstName(customerRegistrationRequest.firstName())
            .lastName(customerRegistrationRequest.lastName())
            .email(customerRegistrationRequest.email())
            .build();
    customerRepository.saveAndFlush(customer);
    FraudCheckResponse fraudCheckResponse =
        restTemplate.getForObject(
            "http://localhost:8081/api/v1/fraud-check/{customerId}",
            FraudCheckResponse.class,
            customer.getId());
    assert fraudCheckResponse != null;
    if (fraudCheckResponse.isFraudster()) {
      throw new IllegalStateException();
    }
  }
}
