package kg.beeline.bankaccountoauth2.mapper;

import kg.beeline.bankaccountoauth2.domain.account.Account;
import kg.beeline.bankaccountoauth2.domain.account.request.AccountOperationRequest;
import kg.beeline.bankaccountoauth2.domain.account.request.AddAccountRequest;
import kg.beeline.bankaccountoauth2.dto.account.AccountDto;
import kg.beeline.bankaccountoauth2.dto.account.request.AccountOperationRequestDto;
import kg.beeline.bankaccountoauth2.dto.account.request.AddAccountRequestDto;
import kg.beeline.bankaccountoauth2.exception.UnAuthorizedException;

public interface AccountMapper {

    AddAccountRequest toAddAccountRequest(AddAccountRequestDto requestDto) throws UnAuthorizedException;

    AccountOperationRequest toAccountOperationRequest(AccountOperationRequestDto requestDto) throws UnAuthorizedException;

    AccountDto toAccountDto(Account account);

}
