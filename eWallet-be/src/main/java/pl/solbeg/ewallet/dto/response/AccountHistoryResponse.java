package pl.solbeg.ewallet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountHistoryResponse {

    private String account;
    private Double quantity;
    private String operation;
    private String operationDate;
}
