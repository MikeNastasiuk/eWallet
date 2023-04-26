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
public class CustomerDataResponse {

    private Long id;
    private String login;
    private String firstName;
    private String lastName;
    private String customerName;
}
