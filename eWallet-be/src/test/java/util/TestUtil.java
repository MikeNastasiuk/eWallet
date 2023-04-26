package util;

import pl.solbeg.ewallet.dto.request.AccountCreateRequest;
import pl.solbeg.ewallet.dto.request.CustomerCreateRequest;
import pl.solbeg.ewallet.dto.request.OperationRequest;
import pl.solbeg.ewallet.dto.response.AccountResponse;
import pl.solbeg.ewallet.dto.response.CurrencyResponse;
import pl.solbeg.ewallet.dto.response.CustomerResponse;
import pl.solbeg.ewallet.entity.Account;
import pl.solbeg.ewallet.entity.AccountHistory;
import pl.solbeg.ewallet.entity.Currency;
import pl.solbeg.ewallet.entity.Customer;
import pl.solbeg.ewallet.entity.OperationEnum;

import java.time.LocalDateTime;
import java.util.List;

public class TestUtil {

    public static List<Currency> createCuurencyList() {
        return List.of(
                createCurrency(1L, "978", "EUR", "Euro"),
                createCurrency(2L,"840", "USD", "US Dollar"),
                createCurrency(3L,"985", "PLN", "Zloty"),
                createCurrency(4L,"826", "GBP", "Pound Sterling"),
                createCurrency(5L,"156", "CNY", "Yuan Renminbi")
        );
    }

    public static List<CurrencyResponse> createCuurencyResponseList() {
        return List.of(
                createCurrencyCurrencyResponse("978", "EUR", "Euro"),
                createCurrencyCurrencyResponse("840", "USD", "US Dollar"),
                createCurrencyCurrencyResponse("985", "PLN", "Zloty"),
                createCurrencyCurrencyResponse("826", "GBP", "Pound Sterling"),
                createCurrencyCurrencyResponse("156", "CNY", "Yuan Renminbi")
        );
    }

    public static CurrencyResponse createCurrencyCurrencyResponse(
                                                                  String code,
                                                                  String name,
                                                                  String description) {
        return CurrencyResponse.builder()
                .code(code)
                .name(name)
                .description(description)
                .build();
    }

    public static Currency createCurrency(Long id,
                                          String code,
                                          String name,
                                          String description) {
        return Currency.builder()
                .id(id)
                .code(code)
                .name(name)
                .description(description)
                .build();
    }

    public static Currency getUSD() {
        return createCurrency(2L,"840", "USD", "US Dollar");
    }

    public static CurrencyResponse getUSDResponse() {
        return createCurrencyCurrencyResponse("840", "USD", "US Dollar");
    }

    public static Customer createCustomer() {
        return Customer.builder()
                .id(1l)
                .login("Ivanov")
                .firstName("Ivan")
                .lastName("Ivanov")
                .build();
    }

    public static CustomerCreateRequest createCustomerRequest() {
        return CustomerCreateRequest.builder()
                .firstName("firstName")
                .lastName("lastName")
                .login("login")
                .password("password")
                .build();
    }

    public static CustomerResponse createCustomerResponse() {
        return CustomerResponse.builder()
                .customerName("firstName lastName")
                .login("login")
                .build();
    }

    public static OperationRequest createReplenishOperation() {
        return OperationRequest.builder()
                .account("")
                .quantity(100.50d)
                .operation(OperationEnum.REPLENISH)
                .build();
    }

    public static OperationRequest createWithdrawOperation() {
        return OperationRequest.builder()
                .account("")
                .quantity(54.50d)
                .operation(OperationEnum.WITHDRAW)
                .build();
    }

    public static Account createAccount() {
        return Account.builder()
                .id(1L)
                .account("123456789")
                .currency(getUSD())
                .amount(250d)
                .description("description")
                .build();
    }

    public static List<AccountHistory> createAccountHistoryList() {
        return List.of(
                createAccountHistory(1l, 23.09, OperationEnum.REPLENISH),
                createAccountHistory(1l, 11.21, OperationEnum.WITHDRAW),
                createAccountHistory(1l, 55.00, OperationEnum.REPLENISH)
        );
    }

    public static AccountHistory createAccountHistory(Long id,
                                                      Double quantity,
                                                      OperationEnum operation) {
        return AccountHistory.builder()
                .id(id)
                .operationDate(LocalDateTime.now())
                .account(createAccount())
                .quantity(quantity)
                .operation(operation)
                .build();
    }

    public static AccountCreateRequest createRequestAccountCreateRequest() {
        return AccountCreateRequest.builder()
                .customerId(1L)
                .currencyCode("840")
                .description("accountDescription")
                .build();
    }

    public static AccountResponse createAccountResponse() {
        return AccountResponse.builder()
                .accountName("accountName")
                .accountCurrency("USD")
                .description("accountDescription")
                .amount(0.00)
                .build();
    }
}
