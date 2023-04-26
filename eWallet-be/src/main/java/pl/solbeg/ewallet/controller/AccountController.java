package pl.solbeg.ewallet.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.solbeg.ewallet.dto.request.AccountCreateRequest;
import pl.solbeg.ewallet.dto.response.AccountResponse;
import pl.solbeg.ewallet.service.AccountService;

import java.util.List;

@RestController
@Tag(name = "Account")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{customerId}/accounts")
    @Operation(summary = "Get all customer accounts", description = "Get all customer accounts by customer ID")
    public ResponseEntity<List<AccountResponse>> getAllUserAccount(@PathVariable Long customerId) {
        return ResponseEntity.ok(accountService.getAllCustomerAccounts(customerId));
    }

    @GetMapping("/{accountName}/account-data")
    @Operation(summary = "Get account data", description = "Get account data by account ID")
    public ResponseEntity<AccountResponse> getAccountData(@PathVariable String accountName) {
        return ResponseEntity.ok(accountService.getAccountData(accountName));
    }

    @PostMapping("/create-account")
    @Operation(summary = "Add customer new account", description = "Create new account for customer")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountCreateRequest accountCreateRequest) {
        return ResponseEntity.ok(accountService.createAccount(accountCreateRequest));
    }

    @DeleteMapping("/delete-account/{accountName}")
    @Operation(summary = "Delete customer account", description = "Delete customer account")
    public ResponseEntity<String> deleteAccount(@PathVariable String accountName) {
        return ResponseEntity.ok(accountService.deleteAccount(accountName));
    }
}