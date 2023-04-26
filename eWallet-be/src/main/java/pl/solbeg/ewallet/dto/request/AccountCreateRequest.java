package pl.solbeg.ewallet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateRequest {

    private Long customerId;

    private String currencyCode;

    private String description;
}
