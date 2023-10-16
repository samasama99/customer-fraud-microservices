package org.samasama.fraud;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class FraudCheckService {
  private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

  public FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
    this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
  }

  public boolean isFraudulentCustomer(Integer customerId) {
    this.fraudCheckHistoryRepository.save(
        FraudCheckHistory.builder()
            .customerId(customerId)
            .isFraudster(false)
            .createdAt(LocalDateTime.now())
            .build());
    return false;
  }
}
