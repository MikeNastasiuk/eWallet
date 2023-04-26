package pl.solbeg.ewallet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.solbeg.ewallet.dto.response.AccountResponse;
import pl.solbeg.ewallet.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "accountName", source = "account")
    @Mapping(target = "accountCurrency", source = "currency.name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "amount", source = "amount")
    AccountResponse accountToResponse(Account account);
}
