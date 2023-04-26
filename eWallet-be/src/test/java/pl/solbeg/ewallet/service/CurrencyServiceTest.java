package pl.solbeg.ewallet.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.solbeg.ewallet.dto.response.CurrencyResponse;
import pl.solbeg.ewallet.entity.Currency;
import pl.solbeg.ewallet.mapper.CurrencyMapper;
import pl.solbeg.ewallet.repository.CurrencyRepository;
import util.TestUtil;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;
    @Mock
    private CurrencyMapper currencyMapper;
    @InjectMocks
    private CurrencyService currencyService;

    @Test
    void findCurrencyByCode() {
        Optional<Currency> currUSD = Optional.of(TestUtil.getUSD());

        when(currencyRepository.findByCode(any(String.class))).thenReturn(currUSD);

        Currency usd = currencyService.findCurrencyByCode("840");

        assertEquals(usd.getCode(), "840");
        assertEquals(usd.getName(), "USD");
    }

    @Test
    void getAllCurrency() {
        List<Currency> currencies = TestUtil.createCuurencyList();
        CurrencyResponse currencyResponse = TestUtil.getUSDResponse();

        when(currencyRepository.findAll()).thenReturn(currencies);
        when(currencyMapper.currencyToResponse(any(Currency.class))).thenReturn(currencyResponse);

        List<CurrencyResponse> currencyResponseList = currencyService.getAllCurrency();

        assertEquals(currencyResponseList.size(), 5);
    }
}