package ddd.refactor.service;

import java.math.BigDecimal;

public interface TransferService {
    Result<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency);
}
