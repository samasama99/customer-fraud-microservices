package org.samasama.email_validation;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class EmailValidationHistory {
    @Id
    @SequenceGenerator(name = "email_validation_id_sequence", sequenceName = "email_validation_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_validation_id_sequence")
    private Integer id;

    private String email;
    private Boolean isValid;
    private LocalDateTime createdAt;
}
