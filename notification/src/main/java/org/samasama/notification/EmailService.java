package org.samasama.notification;

public interface EmailService {
    public void sendSimpleMessage(
            String to, String subject, String text);
}
