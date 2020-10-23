package kg.beeline.bankaccountoauth2.endpoint.account;

import kg.beeline.bankaccountoauth2.dto.account.AccountDto;
import kg.beeline.bankaccountoauth2.dto.account.request.AccountOperationRequestDto;
import kg.beeline.bankaccountoauth2.exception.NotFoundException;
import kg.beeline.bankaccountoauth2.exception.OperationNotValidException;
import kg.beeline.bankaccountoauth2.exception.UnAuthorizedException;

public interface AccountOperationEndpoint {

    AccountDto getAccountInfo() throws NotFoundException, UnAuthorizedException;

    AccountDto cashOut(AccountOperationRequestDto requestDto) throws NotFoundException, UnAuthorizedException, OperationNotValidException;

    AccountDto deposit(AccountOperationRequestDto requestDto) throws NotFoundException, UnAuthorizedException, OperationNotValidException;
}
