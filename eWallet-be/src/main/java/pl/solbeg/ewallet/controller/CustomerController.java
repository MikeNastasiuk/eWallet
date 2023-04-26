package pl.solbeg.ewallet.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.solbeg.ewallet.dto.request.CustomerCreateRequest;
import pl.solbeg.ewallet.dto.response.CustomerDataResponse;
import pl.solbeg.ewallet.dto.response.CustomerResponse;
import pl.solbeg.ewallet.service.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Tag(name = "Customer")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create-customer")
    @Operation(summary = "Create customer", description = "Create new customer")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerCreateRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @GetMapping("/customer-data")
    @Operation(summary = "Get customer data", description = "Get customer data")
    public ResponseEntity<CustomerDataResponse> createCustomer(@Parameter String login) {
        return ResponseEntity.ok(customerService.getCustomerData(login));
    }

    @GetMapping("/login")
    @Operation(summary = "Login", description = "User login")
    public void login(HttpServletRequest request, HttpServletResponse response) {
        if (customerService.isAuthorized(request)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
