package kg.beeline.bankaccountoauth2.endpoint.account;

import kg.beeline.bankaccountoauth2.dto.account.AccountDto;
import kg.beeline.bankaccountoauth2.dto.account.request.AddAccountRequestDto;
import kg.beeline.bankaccountoauth2.exception.NotFoundException;
import kg.beeline.bankaccountoauth2.exception.UnAuthorizedException;

import java.util.List;

public interface AccountEndpoint {

    List<AccountDto> searchForAllAccounts() throws NotFoundException;

    AccountDto addAccount(AddAccountRequestDto requestDto) throws UnAuthorizedException;

}
