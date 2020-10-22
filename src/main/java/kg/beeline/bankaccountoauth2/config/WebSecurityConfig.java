package kg.beeline.bankaccountoauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint((req, resp, err) -> resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED));

        http.cors()
                .and()
                .authorizeRequests()
                    .anyRequest()
                        .authenticated()
                .and()
                .oauth2ResourceServer()
                    .jwt();
    }
}
