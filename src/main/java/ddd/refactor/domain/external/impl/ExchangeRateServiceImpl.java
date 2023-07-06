package ddd.refactor.domain.external.impl;

import ddd.refactor.types.types.Currency;
import ddd.refactor.types.types.ExchangeRate;
import ddd.refactor.domain.external.ExchangeRateService;
import ddd.refactor.domain.external.YahooForexService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    private YahooForexService yahooForexService;

    @Override
    public ExchangeRate getExchangeRate(Currency source, Currency target) {
        if (source.equals(target)) {
            return new ExchangeRate(BigDecimal.ONE, source, target);
        }
        BigDecimal forex = yahooForexService.getExchangeRate(source.getValue(), target.getValue());
        return new ExchangeRate(forex, source, target);
    }
}