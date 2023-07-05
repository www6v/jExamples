package ddd.transactionScript.service;

import ddd.transactionScript.exception.DailyLimitExceededException;
import ddd.transactionScript.exception.InsufficientFundsException;
import ddd.transactionScript.exception.InvalidCurrencyException;
import ddd.transactionScript.domain.Result;

import java.math.BigDecimal;

public interface TransferService {
    Result<Boolean> transfer(Long userId, String targetAccountNumber, BigDecimal amount, String cny) throws InvalidCurrencyException, InsufficientFundsException, DailyLimitExceededException;
}
