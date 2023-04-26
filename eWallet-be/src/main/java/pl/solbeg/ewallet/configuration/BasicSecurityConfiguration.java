package pl.solbeg.ewallet.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.solbeg.ewallet.configuration.security.BasicLoginFilter;
import pl.solbeg.ewallet.service.CustomerService;
import pl.solbeg.ewallet.service.PasswordEncryptionService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class BasicSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncryptionService passwordEncryptionService;
    private final CustomerService customerService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .addFilterAfter(new BasicLoginFilter(passwordEncryptionService, customerService),
                        UsernamePasswordAuthenticationFilter.class);
    }
}
