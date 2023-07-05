package ddd.refactor.impl;

import ddd.refactor.domain.Currency;
import ddd.refactor.service.ExchangeRate;
import ddd.refactor.service.ExchangeRateService;
import ddd.refactor.service.YahooForexService;
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