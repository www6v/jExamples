package ddd.refactor.service;

import ddd.refactor.domain.Account;
import ddd.refactor.domain.Money;

public interface AccountTransferService {
    void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate);
}
