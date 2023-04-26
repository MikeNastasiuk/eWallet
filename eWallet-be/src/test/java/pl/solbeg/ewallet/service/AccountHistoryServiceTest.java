package pl.solbeg.ewallet.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.solbeg.ewallet.dto.request.OperationRequest;
import pl.solbeg.ewallet.dto.response.AccountHistoryResponse;
import pl.solbeg.ewallet.entity.Account;
import pl.solbeg.ewallet.entity.AccountHistory;
import pl.solbeg.ewallet.entity.OperationEnum;
import pl.solbeg.ewallet.exeption.DecodedException;
import pl.solbeg.ewallet.mapper.AccountHistoryMapper;
import pl.solbeg.ewallet.repository.AccountHistoryRepository;
import util.TestUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountHistoryServiceTest {

    @Mock
    private AccountHistoryRepository accountHistoryRepository;
    @Mock
    private AccountHistoryMapper accountHistoryMapper;
    @Mock
    private AccountService accountService;
    @InjectMocks
    private AccountHistoryService accountHistoryService;

    @Test
    void addReplenishOperation() {
        OperationRequest replenishRequest = TestUtil.createReplenishOperation();
        Account account = TestUtil.createAccount();

        when(accountService.findAccountByName(any(String.class))).thenReturn(account);
        when(accountService.saveAccount(any(Account.class))).thenReturn(account);

        String result = accountHistoryService.addOperation(replenishRequest);

        assertEquals(result, "money replenished successfully");
    }

    @Test
    void addWithdrawOperation() {
        OperationRequest withdrawRequest = TestUtil.createWithdrawOperation();
        Account account = TestUtil.createAccount();

        when(accountService.findAccountByName(any(String.class))).thenReturn(account);
        when(accountService.saveAccount(any(Account.class))).thenReturn(account);

        String result = accountHistoryService.addOperation(withdrawRequest);

        assertEquals(result, "money withdraw successfully");
    }

    @Test
    void addWithdrawOperationError() {
        OperationRequest withdrawRequest = TestUtil.createWithdrawOperation();
        withdrawRequest.setQuantity(1000d);
        Account account = TestUtil.createAccount();

        when(accountService.findAccountByName(any(String.class))).thenReturn(account);

        assertThatThrownBy(() -> accountHistoryService.addOperation(withdrawRequest))
                .isInstanceOf(DecodedException.class);
    }

    @Test
    void getAccountHistory() {
        Account account = TestUtil.createAccount();
        List<AccountHistory> accountHistoryList = TestUtil.createAccountHistoryList();
        AccountHistory accountHistory = TestUtil.createAccountHistory(1l, 23.09, OperationEnum.REPLENISH);

        when(accountService.findAccountByName(any(String.class))).thenReturn(account);
        when(accountHistoryRepository.getAccountHistory(any(String.class))).thenReturn(accountHistoryList);
        when(accountHistoryMapper.accountHistoryToResponse(any(AccountHistory.class)))
                .thenReturn(new AccountHistoryResponse());

        List<AccountHistoryResponse> accountHistories =
                accountHistoryService.getAccountHistory(account.getAccount());

        assertEquals(accountHistories.size(), 3);
    }
}