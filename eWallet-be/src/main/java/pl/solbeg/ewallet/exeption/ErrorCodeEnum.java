package pl.solbeg.ewallet.exeption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    CUSTOMER_NOT_FOUND("1001", "Customer not found", HttpStatus.NOT_FOUND),
    CUSTOMER_ALREADY_EXIST("1002", "Customer already exist", HttpStatus.NOT_FOUND),
    CUSTOMER_NOT_LOGGED("1003", "Wrong login information", HttpStatus.UNAUTHORIZED),
    CUSTOMER_LOGIN_FAIL("1004", "Customer login fail", HttpStatus.UNAUTHORIZED),
    CUSTOMER_CREATE_ERROR("1005", "Customer creation error", HttpStatus.INTERNAL_SERVER_ERROR),

    ACCOUNT_NOT_FOUND("2001", "Account not found", HttpStatus.NOT_FOUND),

    CURRENCY_NOT_FOUND("3001", "Currency not found", HttpStatus.NOT_FOUND),

    UNKNOWN_OPERATION("4001", "Unknown operation", HttpStatus.NOT_FOUND),
    OPERATION_FAILED("4002", "Operation failed", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
