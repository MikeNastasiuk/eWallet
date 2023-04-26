package pl.solbeg.ewallet.exeption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
@Setter
@NoArgsConstructor
public class DecodedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4764612876202925373L;

    private HttpStatus httpStatus;
    private String errorCode;

    public DecodedException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.httpStatus = errorCodeEnum.getHttpStatus();
        this.errorCode = errorCodeEnum.getCode();
    }
}
