package ddd.refactor.impl;

import ddd.refactor.domain.Account;
import ddd.refactor.domain.Money;
import ddd.refactor.service.AccountTransferService;
import ddd.refactor.service.ExchangeRate;
import ddd.refactor.service.ExchangeRateService;

public class AccountTransferServiceImpl implements AccountTransferService {
    private ExchangeRateService exchangeRateService;

    @Override
    public void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate) {
        Money sourceMoney = exchangeRate.exchangeTo(targetMoney);
        sourceAccount.deposit(sourceMoney);
        targetAccount.withdraw(targetMoney);
    }
}
