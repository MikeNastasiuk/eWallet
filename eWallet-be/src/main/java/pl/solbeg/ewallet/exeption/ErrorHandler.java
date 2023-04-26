package pl.solbeg.ewallet.exeption;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(DecodedException.class)
    public @ResponseBody ErrorInfo handleException(HttpServletResponse response, DecodedException exception) {
        response.setStatus(exception.getHttpStatus().value());
        return new ErrorInfo(exception.getErrorCode(), exception.getMessage());
    }

//    @ExceptionHandler(DecodedException.class)
//    public ResponseEntity<ErrorInfo> handleNoSuchResourceException(DecodedException exception) {
//        ErrorInfo errors = new ErrorInfo();
//        errors.setErrorCode(exception.getErrorCode());
//        errors.setErrorMessage(exception.getMessage());
//
//        return new ResponseEntity<>(errors, exception.getHttpStatus());
//    }
}
