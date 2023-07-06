package ddd.refactor.application.impl;

import ddd.refactor.application.TransferService;
import ddd.refactor.domain.domain.entity.Account;
import ddd.refactor.domain.domain.service.AccountTransferService;
import ddd.refactor.domain.domain.types.AuditMessage;
import ddd.refactor.domain.external.ExchangeRateService;
import ddd.refactor.persistence.repository.impl.AccountRepository;
import ddd.refactor.domain.messaging.AuditMessageProducer;
import ddd.refactor.types.types.*;

import java.math.BigDecimal;

/// https://zhuanlan.zhihu.com/p/343388831

public class TransferServiceImplNew implements TransferService {

    private AccountRepository accountRepository;
    private AuditMessageProducer auditMessageProducer;
    private ExchangeRateService exchangeRateService;
    private AccountTransferService accountTransferService;

    @Override
    public Result<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency) {
        // 参数校验
        Money targetMoney = new Money(targetAmount, new Currency(targetCurrency));

        // 读数据
        Account sourceAccount = accountRepository.find(new UserId(sourceUserId));
        Account targetAccount = accountRepository.find(new AccountNumber(targetAccountNumber));
        ExchangeRate exchangeRate = exchangeRateService.getExchangeRate(sourceAccount.getCurrency(), targetMoney.getCurrency());

        // 业务逻辑
        accountTransferService.transfer(sourceAccount, targetAccount, targetMoney, exchangeRate);

        // 保存数据
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        // 发送审计消息
        AuditMessage message = new AuditMessage(sourceAccount, targetAccount, targetMoney);
        auditMessageProducer.send(message);

        return Result.success(true);
    }
}
