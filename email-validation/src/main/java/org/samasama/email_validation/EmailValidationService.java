package org.samasama.email_validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
@Slf4j
public class EmailValidationService {
    private final EmailValidationRepository emailValidationRepository;
    private final RestTemplate restTemplate;

    @Value("${zero-bounce.api-key}")
    private String zeroBounceApiKey;

    public EmailValidationService(EmailValidationRepository emailValidationRepository, RestTemplate restTemplate) {
        this.emailValidationRepository = emailValidationRepository;
        this.restTemplate = restTemplate;
    }

    public boolean isValid(String email) {
        String baseURL = "https://api.zerobounce.net/v2/validate?api_key=";
        String encodedApiKey = URLEncoder.encode(zeroBounceApiKey, StandardCharsets.UTF_8);
        String encodedEmail = URLEncoder.encode(email, StandardCharsets.UTF_8);
        URI requestURI = URI.create(baseURL + encodedApiKey + "&email=" + encodedEmail);
        EmailValidationResponse response = restTemplate.getForObject(
                requestURI.toString(), EmailValidationResponse.class);
        assert response != null;
        log.info(response.toString());
        boolean isValid = response.getStatus().equals("valid");
        this.emailValidationRepository.save(
                EmailValidationHistory.builder()
                        .email(email)
                        .isValid(isValid)
                        .createdAt(LocalDateTime.now())
                        .build());
        return isValid;
    }
}
