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
public class CustomerCreateRequest {

    private String firstName;
    private String lastName;
    private String login;
    private String password;
}
