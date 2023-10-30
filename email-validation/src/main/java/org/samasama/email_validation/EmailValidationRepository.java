package org.samasama.email_validation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailValidationRepository extends JpaRepository<EmailValidationHistory, Integer> {
}
