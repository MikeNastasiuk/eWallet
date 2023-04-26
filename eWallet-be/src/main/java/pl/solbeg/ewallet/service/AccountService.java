package pl.solbeg.ewallet.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.solbeg.ewallet.dto.request.AccountCreateRequest;
import pl.solbeg.ewallet.dto.response.AccountResponse;
import pl.solbeg.ewallet.entity.Account;
import pl.solbeg.ewallet.entity.Currency;
import pl.solbeg.ewallet.entity.Customer;
import pl.solbeg.ewallet.exeption.DecodedException;
import pl.solbeg.ewallet.exeption.ErrorCodeEnum;
import pl.solbeg.ewallet.mapper.AccountMapper;
import pl.solbeg.ewallet.repository.AccountRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepository;
    private final CurrencyService currencyService;
    private final CustomerService customerService;
    private final AccountMapper accountMapper;

    @Transactional
    public String deleteAccount(String accountName) {
        Account account = findAccountByName(accountName);
        String accName = account.getAccount();
        accountRepository.delete(account);

        return "account " + accName + " successfully deleted";
    }

    @Transactional
    public AccountResponse createAccount(AccountCreateRequest accountCreateRequest) {
        Account account = newAccount(accountCreateRequest);

        return accountMapper.accountToResponse(saveAccount(account));
    }

    public List<AccountResponse> getAllCustomerAccounts(Long userId) {
        Customer customer = customerService.findCustomerById(userId);
        List<Account> accounts = accountRepository.findAllByCustomer(customer);

        if (CollectionUtils.isEmpty(accounts)) {
            return Collections.emptyList();
        }

        return accounts.stream().map(accountMapper::accountToResponse).collect(Collectors.toList());
    }

    public AccountResponse getAccountData(String accountName) {
        return accountMapper.accountToResponse(findAccountByName(accountName));
    }

    public Account findAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new DecodedException(ErrorCodeEnum.ACCOUNT_NOT_FOUND));
    }

    public Account findAccountByName(String account) {
        return accountRepository.findByAccountEquals(account)
                .orElseThrow(() -> new DecodedException(ErrorCodeEnum.ACCOUNT_NOT_FOUND));
    }

    private Account newAccount(AccountCreateRequest accountCreateRequest) {
        Currency currency = currencyService.findCurrencyByCode(accountCreateRequest.getCurrencyCode());
        Customer customer = customerService.findCustomerById(accountCreateRequest.getCustomerId());

        Account account = new Account();
        account.setAccount(generateAccountName(currency.getName()));
        account.setCurrency(currency);
        account.setCustomer(customer);
        if (!StringUtils.isEmpty(accountCreateRequest.getDescription())) {
            account.setDescription(accountCreateRequest.getDescription());
        }
        account.setAmount(0.0);

        return account;
    }

    private String generateAccountName(String curr) {
        UUID uuid = UUID.randomUUID();
        String sUuid = uuid.toString().replaceAll("-", "").toUpperCase();
        return curr.concat("-").concat(sUuid);
    }

    @Transactional
    public Account saveAccount(Account entity) {
        return accountRepository.save(entity);
    }

    @Transactional
    public void saveAllAccounts(List<Account> accounts) {
        accountRepository.saveAll(accounts);
    }
}
