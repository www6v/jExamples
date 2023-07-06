package ddd.refactor.domain.domain.service.impl;

import ddd.refactor.domain.domain.entity.Account;
import ddd.refactor.domain.domain.service.AccountTransferService;
import ddd.refactor.types.types.Money;
import ddd.refactor.types.types.ExchangeRate;
import ddd.refactor.domain.external.ExchangeRateService;

public class AccountTransferServiceImpl implements AccountTransferService {
    private ExchangeRateService exchangeRateService;

    @Override
    public void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate) {
        Money sourceMoney = exchangeRate.exchangeTo(targetMoney);
        sourceAccount.deposit(sourceMoney);
        targetAccount.withdraw(targetMoney);
    }
}
