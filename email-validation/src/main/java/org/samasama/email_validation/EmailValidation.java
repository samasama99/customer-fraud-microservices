package org.samasama.email_validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EmailValidation {
    public static void main(String[] args) {
        SpringApplication.run(EmailValidation.class, args);
    }
}
