package pl.solbeg.ewallet.exeption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {

    private String errorCode;
    private String errorMessage;
}
