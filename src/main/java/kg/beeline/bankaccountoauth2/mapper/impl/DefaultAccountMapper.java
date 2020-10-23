package kg.beeline.bankaccountoauth2.mapper.impl;

import kg.beeline.bankaccountoauth2.domain.account.Account;
import kg.beeline.bankaccountoauth2.domain.account.request.AccountOperationRequest;
import kg.beeline.bankaccountoauth2.domain.account.request.AddAccountRequest;
import kg.beeline.bankaccountoauth2.dto.account.AccountDto;
import kg.beeline.bankaccountoauth2.dto.account.request.AccountOperationRequestDto;
import kg.beeline.bankaccountoauth2.dto.account.request.AddAccountRequestDto;
import kg.beeline.bankaccountoauth2.exception.UnAuthorizedException;
import kg.beeline.bankaccountoauth2.mapper.AccountMapper;
import kg.beeline.bankaccountoauth2.service.user.UserContextProvider;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DefaultAccountMapper implements AccountMapper {

    private final UserContextProvider userContextProvider;

    public DefaultAccountMapper(UserContextProvider userContextProvider) {
        this.userContextProvider = userContextProvider;
    }

    @Override
    public AddAccountRequest toAddAccountRequest(AddAccountRequestDto requestDto)
            throws UnAuthorizedException
    {
        String accountUserUid = Objects.nonNull(requestDto.getUserUid())
                ? requestDto.getUserUid()
                : userContextProvider.getCurrentAccountPrincipal().getName();

        Boolean accountEnabled = Objects.nonNull(requestDto.getEnabled()) ? requestDto.getEnabled() : true;

        return AddAccountRequest
                .builder()
                .enabled(accountEnabled)
                .userUid(accountUserUid)
                .initialAmount(requestDto.getInitialAmount())
                .build();
    }

    @Override
    public AccountOperationRequest toAccountOperationRequest(AccountOperationRequestDto requestDto)
            throws UnAuthorizedException
    {
        String accountUserUid = userContextProvider.getCurrentAccountPrincipal().getName();

        return  AccountOperationRequest
                .builder()
                .userUid(accountUserUid)
                .amount(requestDto.getAmount())
                .build();
    }

    @Override
    public AccountDto toAccountDto(Account account) {
        return  AccountDto
                .builder()
                .id(account.getId())
                .createdAt(account.getCreatedAt())
                .enabled(account.getEnabled())
                .userUid(account.getUserUid())
                .amount(account.getAmount())
                .build();
    }
}
