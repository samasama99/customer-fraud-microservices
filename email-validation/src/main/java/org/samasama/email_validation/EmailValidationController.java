package org.samasama.email_validation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.samasama.clients.email_validation.EmailValidationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/email-validation")
@AllArgsConstructor
@Slf4j
public class EmailValidationController {
    private final EmailValidationService emailValidationService;

    @GetMapping(path = "{email}")
    public EmailValidationResponse isValid(@PathVariable("email") String email) {
        boolean isValidEmail = emailValidationService.isValid(email);
        log.info("email_validation check request for customer {}", email);
        return new EmailValidationResponse(isValidEmail);
    }
}
