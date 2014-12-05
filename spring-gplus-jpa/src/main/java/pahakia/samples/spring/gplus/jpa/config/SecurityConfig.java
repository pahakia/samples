package pahakia.samples.spring.gplus.jpa.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
            .antMatchers("/gplus").permitAll()
            .antMatchers("/public**").permitAll()
            .antMatchers("/user**").access("hasRole('ROLE_USER')")
            .antMatchers("/admin**").access("hasRole('ROLE_ADMIN')")
            .antMatchers("/loggedin").permitAll()
            .and()
            .formLogin().loginPage("/");
        // @formatter:on
        RequestHeaderPreAuthenticatedProcessingFilterImpl filter =
                new RequestHeaderPreAuthenticatedProcessingFilterImpl();
        filter.setAuthenticationManager(authenticationManager());
        filter.setCheckForPrincipalChanges(false);
        http.addFilterAfter(filter, AbstractPreAuthenticatedProcessingFilter.class);
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        List<AuthenticationProvider> authProviderList = new ArrayList<AuthenticationProvider>();
        authProviderList.add(new AuthenticationProviderImpl());
        AuthenticationManager authenticationManager = new ProviderManager(authProviderList);
        return authenticationManager;
    }

}
