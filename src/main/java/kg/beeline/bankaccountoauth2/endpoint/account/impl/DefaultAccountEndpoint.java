package kg.beeline.bankaccountoauth2.endpoint.account.impl;

import kg.beeline.bankaccountoauth2.domain.account.Account;
import kg.beeline.bankaccountoauth2.domain.account.request.AddAccountRequest;
import kg.beeline.bankaccountoauth2.dto.account.AccountDto;
import kg.beeline.bankaccountoauth2.dto.account.request.AddAccountRequestDto;
import kg.beeline.bankaccountoauth2.endpoint.account.AccountEndpoint;
import kg.beeline.bankaccountoauth2.exception.NotFoundException;
import kg.beeline.bankaccountoauth2.exception.UnAuthorizedException;
import kg.beeline.bankaccountoauth2.mapper.AccountMapper;
import kg.beeline.bankaccountoauth2.service.user.UserContextProvider;
import kg.beeline.bankaccountoauth2.service.account.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultAccountEndpoint implements AccountEndpoint {

    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final UserContextProvider userContextProvider;

    public DefaultAccountEndpoint(
            AccountService accountService,
            AccountMapper accountMapper,
            UserContextProvider userContextProvider
    ) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
        this.userContextProvider = userContextProvider;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDto> searchForAllAccounts()
            throws NotFoundException
    {
        List<Account> accounts = accountService.getAll();
        List<AccountDto> accountDtos = new ArrayList<>();

        for (Account account : accounts){
            accountDtos.add(accountMapper.toAccountDto(account));
        }

        return accountDtos;
    }

    @Override
    @Transactional
    public AccountDto addAccount(AddAccountRequestDto requestDto)
            throws UnAuthorizedException
    {
        AddAccountRequest externalRequest = accountMapper.toAddAccountRequest(requestDto);
        Account newAccount = accountService.create(externalRequest);

        return accountMapper.toAccountDto(newAccount);
    }
}
