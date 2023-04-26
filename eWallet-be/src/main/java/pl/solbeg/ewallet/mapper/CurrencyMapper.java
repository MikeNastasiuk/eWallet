package pl.solbeg.ewallet.mapper;

import org.mapstruct.Mapper;
import pl.solbeg.ewallet.dto.response.CurrencyResponse;
import pl.solbeg.ewallet.entity.Currency;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    CurrencyResponse currencyToResponse(Currency currency);
}
