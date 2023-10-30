package org.samasama.clients.email_validation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "email-validation")
public interface EmailValidationClient {
    @GetMapping(path = "api/v1/email-validation/{email}")
    EmailValidationResponse isValid(@PathVariable("email") String email);
}
