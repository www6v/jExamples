package ddd.refactor.persistence.repository.impl;

import ddd.refactor.types.types.AccountNumber;
import ddd.refactor.types.types.UserId;
import ddd.refactor.domain.domain.entity.Account;
import ddd.refactor.types.types.AccountId;

public interface AccountRepository {
    Account find(AccountId id);
    Account find(AccountNumber accountNumber);
    Account find(UserId userId);
    Account save(Account account);
}