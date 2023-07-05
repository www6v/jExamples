package ddd.refactor.service;

import ddd.refactor.domain.Currency;

public interface ExchangeRateService {
    ExchangeRate getExchangeRate(Currency source, Currency target);
}
