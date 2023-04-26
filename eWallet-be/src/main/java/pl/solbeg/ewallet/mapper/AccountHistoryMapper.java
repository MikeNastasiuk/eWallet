package pl.solbeg.ewallet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.solbeg.ewallet.dto.response.AccountHistoryResponse;
import pl.solbeg.ewallet.entity.AccountHistory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Mapper(componentModel = "spring")
public interface AccountHistoryMapper {

    @Mapping(target = "account", source = "account.account")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "operation", expression = "java(accountHistory.getOperation().name())")
    @Mapping(target = "operationDate", expression = "java(setOperationDate(accountHistory.getOperationDate()))")
    AccountHistoryResponse accountHistoryToResponse(AccountHistory accountHistory);

    default String setOperationDate(LocalDateTime date) {
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss", Locale.ENGLISH);
        return date.format(FORMATTER);
    }
}
