package pl.solbeg.ewallet.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.solbeg.ewallet.dto.response.CurrencyResponse;
import pl.solbeg.ewallet.service.CurrencyService;

import java.util.List;

@RestController
@Tag(name = "Currency")
@RequiredArgsConstructor
@RequestMapping("/api/v1/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/all")
    @Operation(summary = "Get all currencies", description = "Get currency list")
    public ResponseEntity<List<CurrencyResponse>> getAllCurrency() {
        return ResponseEntity.ok(currencyService.getAllCurrency());
    }

    @GetMapping("/{currencyCode}")
    @Operation(summary = "Get currency by code", description = "Get currency by code")
    public ResponseEntity<CurrencyResponse> getCurrency(@PathVariable String currencyCode) {
        return ResponseEntity.ok(currencyService.getCurrencyResponseByCode(currencyCode));
    }
}