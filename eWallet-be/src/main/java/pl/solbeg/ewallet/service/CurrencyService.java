package pl.solbeg.ewallet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.solbeg.ewallet.dto.response.CurrencyResponse;
import pl.solbeg.ewallet.entity.Currency;
import pl.solbeg.ewallet.exeption.DecodedException;
import pl.solbeg.ewallet.exeption.ErrorCodeEnum;
import pl.solbeg.ewallet.mapper.CurrencyMapper;
import pl.solbeg.ewallet.repository.CurrencyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;

    public Currency findCurrencyByCode(String currencyCode) {
        log.info("Get currency by code: {}", currencyCode);

        return currencyRepository.findByCode(currencyCode)
                .orElseThrow(() -> new DecodedException(ErrorCodeEnum.CURRENCY_NOT_FOUND));
    }

    public CurrencyResponse getCurrencyResponseByCode(String currencyCode) {
        log.info("Get currency by code: {}", currencyCode);

        return currencyMapper.currencyToResponse(findCurrencyByCode(currencyCode));
    }

    public List<CurrencyResponse> getAllCurrency() {
        List<Currency> currencies = currencyRepository.findAll();

        return currencies.stream()
                .map(currencyMapper::currencyToResponse)
                .collect(Collectors.toList());
    }
}
