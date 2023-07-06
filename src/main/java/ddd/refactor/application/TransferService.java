package ddd.refactor.application;

import java.math.BigDecimal;

public interface TransferService {
    Result<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency);

    class Result<T> {
        public static Result<Boolean> success(boolean b) {
            return null;
        }
    }
}
