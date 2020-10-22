package kg.beeline.bankaccountoauth2.service.impl;

import kg.beeline.bankaccountoauth2.exception.UnAuthorizedException;
import kg.beeline.bankaccountoauth2.service.UserContextProvider;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserContextProvider implements UserContextProvider {

    @Override
    public Authentication getCurrentAccountPrincipal() throws UnAuthorizedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
                return authentication;
        }

        throw new UnAuthorizedException("Невозможно получить текущего пользователя.");
    }
}
