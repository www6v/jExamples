package ddd.refactor.repo;

import ddd.refactor.domain.AccountNumber;
import ddd.refactor.domain.UserId;
import ddd.refactor.domain.Account;
import ddd.refactor.domain.AccountId;

public interface AccountRepository {
    Account find(AccountId id);
    Account find(AccountNumber accountNumber);
    Account find(UserId userId);
    Account save(Account account);
}