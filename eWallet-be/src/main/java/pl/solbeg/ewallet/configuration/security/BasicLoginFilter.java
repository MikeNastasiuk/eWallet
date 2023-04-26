package pl.solbeg.ewallet.configuration.security;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.solbeg.ewallet.dto.TokenData;
import pl.solbeg.ewallet.entity.Customer;
import pl.solbeg.ewallet.service.CustomerService;
import pl.solbeg.ewallet.service.PasswordEncryptionService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class BasicLoginFilter extends OncePerRequestFilter {

    private final PasswordEncryptionService passwordEncryptionService;
    private final CustomerService customerService;

    public BasicLoginFilter(PasswordEncryptionService passwordEncryptionService,
                            CustomerService customerService) {
        this.passwordEncryptionService = passwordEncryptionService;
        this.customerService = customerService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        Customer customer = null;
        String login = "";
        String password = "";

        if (token != null) {
            token = getBasicAuthorizationToken(token);
            TokenData tokenData = passwordEncryptionService.getTokenData(token);
            login = tokenData.getLogin();
            password = tokenData.getPassword();
            customer = customerService.checkCustomerByLogin(login);
        }

        if (token == null || customer == null || !passwordEncryptionService.authenticate(password, customer.getPassword(), customer.getSalt())) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        List<String> path = List.of("/login", "/create-customer", "/swagger-ui", "/favicon.ico", "/api-docs");
        String servletPath = request.getServletPath();

        return path.stream().anyMatch(servletPath::contains);
    }

    private String getBasicAuthorizationToken(String token) {
        return token.replaceAll("Basic ", "");
    }
}
