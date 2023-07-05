package ddd.transactionScript.controller;

import ddd.transactionScript.domain.Result;
import ddd.transactionScript.exception.DailyLimitExceededException;
import ddd.transactionScript.exception.InsufficientFundsException;
import ddd.transactionScript.exception.InvalidCurrencyException;
import ddd.transactionScript.service.TransferService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class TransferController {

    private TransferService transferService;

    public Result<Boolean> transfer(String targetAccountNumber, BigDecimal amount, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        try {
            return transferService.transfer(userId, targetAccountNumber, amount, "CNY");
        } catch (InvalidCurrencyException e) {
            throw new RuntimeException(e);
        } catch (InsufficientFundsException e) {
            throw new RuntimeException(e);
        } catch (DailyLimitExceededException e) {
            throw new RuntimeException(e);
        }
    }
}
