package pl.solbeg.ewallet.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.solbeg.ewallet.dto.request.AccountCreateRequest;
import pl.solbeg.ewallet.dto.response.AccountResponse;
import pl.solbeg.ewallet.entity.Account;
import pl.solbeg.ewallet.entity.Currency;
import pl.solbeg.ewallet.entity.Customer;
import pl.solbeg.ewallet.mapper.AccountMapper;
import pl.solbeg.ewallet.repository.AccountRepository;
import util.TestUtil;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CurrencyService currencyService;
    @Mock
    private CustomerService customerService;
    @Mock
    private AccountMapper accountMapper;
    @InjectMocks
    private AccountService accountService;

    @Test
    void createAccount() {
        AccountCreateRequest request = TestUtil.createRequestAccountCreateRequest();
        AccountResponse response = TestUtil.createAccountResponse();
        Customer customer = TestUtil.createCustomer();
        Currency currency = TestUtil.getUSD();
        Account account = TestUtil.createAccount();

        when(currencyService.findCurrencyByCode(any(String.class))).thenReturn(currency);
        when(customerService.findCustomerById(any(Long.class))).thenReturn(customer);
        when(accountMapper.accountToResponse(any(Account.class))).thenReturn(response);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountResponse accountResponse = accountService.createAccount(request);

        assertEquals(accountResponse.getAccountCurrency(), "USD");
        assertEquals(accountResponse.getAccountName(), "accountName");
        assertEquals(accountResponse.getDescription(), "accountDescription");
        assertEquals(accountResponse.getAmount(), Double.valueOf("0.00"));
    }

    @Test
    void getAllCustomerAccounts() {
        Customer customer = TestUtil.createCustomer();
        List<Account> accounts = List.of(TestUtil.createAccount(),
                TestUtil.createAccount(),
                TestUtil.createAccount()
        );

        when(customerService.findCustomerById(any(Long.class))).thenReturn(customer);
        when(accountRepository.findAllByCustomer(any(Customer.class))).thenReturn(accounts);

        List<AccountResponse> accountResponses = accountService.getAllCustomerAccounts(1L);

        assertEquals(accountResponses.size(), 3);
    }

    @Test
    void getAccountData() {
        AccountResponse response = TestUtil.createAccountResponse();
        Account account = TestUtil.createAccount();

        when(accountRepository.findByAccountEquals(any(String.class))).thenReturn(Optional.of(account));
        when(accountMapper.accountToResponse(any(Account.class))).thenReturn(response);

        AccountResponse accountResponse = accountService.getAccountData("123456789");

        assertEquals(accountResponse.getAccountCurrency(), "USD");
        assertEquals(accountResponse.getAccountName(), "accountName");
        assertEquals(accountResponse.getDescription(), "accountDescription");
        assertEquals(accountResponse.getAmount(), Double.valueOf("0.00"));
    }
}