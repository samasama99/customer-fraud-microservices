package org.samasama.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(
        scanBasePackages = {
                "org.samasama.notification",
                "org.samasama.amqp",
        })
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    //  @Bean
    //  CommandLineRunner commandLineRunner(
    //      RabbitMQMessageProducer producer, NotificationConfig notificationConfig) {
    //    return args -> {
    //      producer.publish(
    //          new NotificationRequest(1, "test", "test"),
    //          notificationConfig.getInternalExchange(),
    //          notificationConfig.getInternalNotificationRoutingKey());
    //    };
    //  }
}
