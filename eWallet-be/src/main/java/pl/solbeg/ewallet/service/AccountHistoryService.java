package pl.solbeg.ewallet.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.solbeg.ewallet.dto.request.OperationRequest;
import pl.solbeg.ewallet.dto.request.TransactionRequest;
import pl.solbeg.ewallet.dto.response.AccountHistoryResponse;
import pl.solbeg.ewallet.entity.Account;
import pl.solbeg.ewallet.entity.AccountHistory;
import pl.solbeg.ewallet.entity.OperationEnum;
import pl.solbeg.ewallet.exeption.DecodedException;
import pl.solbeg.ewallet.exeption.ErrorCodeEnum;
import pl.solbeg.ewallet.mapper.AccountHistoryMapper;
import pl.solbeg.ewallet.repository.AccountHistoryRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountHistoryService {

    private final AccountHistoryRepository accHistoryRepository;
    private final AccountHistoryMapper accHistoryMapper;
    private final AccountService accountService;

    @Transactional
    public String addOperation(OperationRequest operationRequest) {
        Account account = accountService.findAccountByName(operationRequest.getAccount());

        Double amountToSet;
        String operation;
        switch (operationRequest.getOperation()) {
            case WITHDRAW -> {
                if (account.getAmount() < operationRequest.getQuantity()) {
                    throw new DecodedException(ErrorCodeEnum.OPERATION_FAILED);
                }
                amountToSet = account.getAmount() - operationRequest.getQuantity();
                operation = "withdraw";
            }
            case REPLENISH -> {
                amountToSet = account.getAmount() + operationRequest.getQuantity();
                operation = "replenished";
            }
            default -> {
                throw new DecodedException(ErrorCodeEnum.UNKNOWN_OPERATION);
            }
        }
        createOperation(account, operationRequest);
        account.setAmount(amountToSet);
        accountService.saveAccount(account);

        return "money " + operation + " successfully";
    }

    //TODO can be added addTransaction() with currency conversion
    @Transactional
    public String addTransaction(TransactionRequest transactionRequest) {
        Account accountFrom = accountService.findAccountByName(transactionRequest.getAccountFrom());
        Account accountTo = accountService.findAccountByName(transactionRequest.getAccountTo());

        if (!accountFrom.getCurrency().getCode().equals(accountTo.getCurrency().getCode())) {
            throw new DecodedException(ErrorCodeEnum.OPERATION_FAILED);
        }

        if (accountFrom.getAmount() < transactionRequest.getQuantity()) {
            throw new DecodedException(ErrorCodeEnum.OPERATION_FAILED);
        }

        accountFrom.setAmount(accountFrom.getAmount() - transactionRequest.getQuantity());
        createOperation(accountFrom, OperationEnum.WITHDRAW, transactionRequest.getQuantity());
        accountTo.setAmount(accountTo.getAmount() + transactionRequest.getQuantity());
        createOperation(accountTo, OperationEnum.REPLENISH, transactionRequest.getQuantity());

        accountService.saveAllAccounts(List.of(accountFrom, accountTo));

        return "transaction completed";
    }

    private void createOperation(Account account, OperationEnum operation, Double quantity) {
        AccountHistory accountHistory = new AccountHistory();
        accountHistory.setAccount(account);
        accountHistory.setQuantity(quantity);
        accountHistory.setOperationDate(LocalDateTime.now());
        accountHistory.setOperation(operation);

        saveAccountHistory(accountHistory);
    }

    private void createOperation(Account account, OperationRequest operationRequest) {
        createOperation(account, operationRequest.getOperation(), operationRequest.getQuantity());
    }

    public List<AccountHistoryResponse> getAccountHistory(String account) {
        accountService.findAccountByName(account);
        List<AccountHistory> accountHistory = accHistoryRepository.getAccountHistory(account);
        if (CollectionUtils.isEmpty(accountHistory)) {
            return Collections.emptyList();
        }

        return accountHistory.stream()
                .map(accHistoryMapper::accountHistoryToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public AccountHistory saveAccountHistory(AccountHistory entity) {
        return accHistoryRepository.save(entity);
    }
}