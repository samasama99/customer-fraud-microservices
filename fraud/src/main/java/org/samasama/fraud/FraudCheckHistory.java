package org.samasama.fraud;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FraudCheckHistory {
  @Id
  @SequenceGenerator(name = "fraud_id_sequence", sequenceName = "fraud_id_sequence")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fraud_id_sequence")
  private Integer id;

  private Integer customerId;
  private Boolean isFraudster;
  private LocalDateTime createdAt;

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass =
        o instanceof HibernateProxy
            ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
            : o.getClass();
    Class<?> thisEffectiveClass =
        this instanceof HibernateProxy
            ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
            : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    FraudCheckHistory that = (FraudCheckHistory) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy
        ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
        : getClass().hashCode();
  }
}
