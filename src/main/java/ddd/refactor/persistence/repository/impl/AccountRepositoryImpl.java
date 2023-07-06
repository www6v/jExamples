package ddd.refactor.persistence.repository.impl;

import ddd.refactor.persistence.persistence.AccountMapper;
import ddd.refactor.types.types.AccountNumber;
import ddd.refactor.types.types.UserId;
import ddd.refactor.domain.domain.entity.Account;
import ddd.refactor.persistence.persistence.AccountBuilder;
import ddd.refactor.persistence.persistence.AccountDO;
import ddd.refactor.types.types.AccountId;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private AccountMapper accountDAO;

    @Autowired
    private AccountBuilder accountBuilder;

    @Override
    public Account find(AccountId id) {
        AccountDO accountDO = accountDAO.selectById(id.getValue());
        return accountBuilder.toAccount(accountDO);
    }

    @Override
    public Account find(AccountNumber accountNumber) {
        AccountDO accountDO = accountDAO.selectByAccountNumber(accountNumber.getValue());
        return accountBuilder.toAccount(accountDO);
    }

    @Override
    public Account find(UserId userId) {
        AccountDO accountDO = accountDAO.selectByUserId(userId.getId());
        return accountBuilder.toAccount(accountDO);
    }

    @Override
    public Account save(Account account) {
        AccountDO accountDO = accountBuilder.fromAccount(account);
        if (accountDO.getId() == null) {
            accountDAO.insert(accountDO);
        } else {
            accountDAO.update(accountDO);
        }
        return accountBuilder.toAccount(accountDO);
    }

}