package pl.solbeg.ewallet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.solbeg.ewallet.entity.OperationEnum;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationRequest {

    private String account;
    private Double quantity;
    private OperationEnum operation;
}
