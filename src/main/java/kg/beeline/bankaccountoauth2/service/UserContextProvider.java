package kg.beeline.bankaccountoauth2.service;

import kg.beeline.bankaccountoauth2.exception.UnAuthorizedException;
import org.springframework.security.core.Authentication;

public interface UserContextProvider {

    Authentication getCurrentAccountPrincipal() throws UnAuthorizedException;

}
