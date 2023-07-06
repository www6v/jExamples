package ddd.refactor.domain.domain.service;

import ddd.refactor.domain.domain.entity.Account;
import ddd.refactor.types.types.ExchangeRate;
import ddd.refactor.types.types.Money;

public interface AccountTransferService {
    void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate);
}
