package com.samasama.clients.notification;

public record NotificationRequest(Integer toCustomerId, String toCustomerEmail, String message) {}
