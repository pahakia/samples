package pahakia.samples.spring.gplus.config;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class AuthenticationProviderImpl implements AuthenticationProvider {

    @SuppressWarnings("unchecked")
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Object[] objs = (Object[]) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = (Collection<GrantedAuthority>) objs[1];
        String token = (String) objs[0];
        PreAuthenticatedAuthenticationToken auth = new PreAuthenticatedAuthenticationToken(token, null, authorities);
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}