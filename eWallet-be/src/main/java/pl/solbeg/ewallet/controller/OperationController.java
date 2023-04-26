package pl.solbeg.ewallet.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.solbeg.ewallet.dto.request.OperationRequest;
import pl.solbeg.ewallet.dto.request.TransactionRequest;
import pl.solbeg.ewallet.dto.response.AccountHistoryResponse;
import pl.solbeg.ewallet.service.AccountHistoryService;

import java.util.List;

@RestController
@Tag(name = "Account Operations")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OperationController {

    private final AccountHistoryService accHistoryService;

    @PostMapping("/account/add-operation")
    @Operation(summary = "Create account operation", description = "Create operation on account")
    public ResponseEntity<String> addOperation(@RequestBody OperationRequest operationRequest) {
        return ResponseEntity.ok(accHistoryService.addOperation(operationRequest));
    }

    @GetMapping("/{account}/operations")
    @Operation(summary = "Get account operations", description = "Get all account operations")
    public ResponseEntity<List<AccountHistoryResponse>> getAccountHistory(@PathVariable String account) {
        return ResponseEntity.ok(accHistoryService.getAccountHistory(account));
    }

    @PostMapping("/account/add-transaction")
    @Operation(summary = "Create account transaction", description = "Create operation between accounts")
    public ResponseEntity<String> addTransaction(@RequestBody TransactionRequest transactionRequest) {
        return ResponseEntity.ok(accHistoryService.addTransaction(transactionRequest));
    }
}
