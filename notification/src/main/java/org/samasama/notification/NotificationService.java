package org.samasama.notification;

import lombok.AllArgsConstructor;
import org.samasama.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.toCustomerId())
                        .toCustomerEmail(notificationRequest.toCustomerEmail())
                        .sender("samasama")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build());
    }
}
