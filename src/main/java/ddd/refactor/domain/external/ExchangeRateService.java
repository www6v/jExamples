package ddd.refactor.domain.external;

import ddd.refactor.types.types.Currency;
import ddd.refactor.types.types.ExchangeRate;

public interface ExchangeRateService {
    ExchangeRate getExchangeRate(Currency source, Currency target);
}
