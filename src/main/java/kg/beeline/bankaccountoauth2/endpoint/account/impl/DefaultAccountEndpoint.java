package kg.beeline.bankaccountoauth2.endpoint.account.impl;

import kg.beeline.bankaccountoauth2.domain.account.Account;
import kg.beeline.bankaccountoauth2.domain.account.request.AccountOperationRequest;
import kg.beeline.bankaccountoauth2.domain.account.request.AddAccountRequest;
import kg.beeline.bankaccountoauth2.dto.account.AccountDto;
import kg.beeline.bankaccountoauth2.dto.account.request.AccountOperationRequestDto;
import kg.beeline.bankaccountoauth2.dto.account.request.AddAccountRequestDto;
import kg.beeline.bankaccountoauth2.endpoint.account.AccountEndpoint;
import kg.beeline.bankaccountoauth2.exception.NotFoundException;
import kg.beeline.bankaccountoauth2.exception.OperationNotValidException;
import kg.beeline.bankaccountoauth2.exception.UnAuthorizedException;
import kg.beeline.bankaccountoauth2.mapper.AccountMapper;
import kg.beeline.bankaccountoauth2.service.UserContextProvider;
import kg.beeline.bankaccountoauth2.service.account.AccountOperationService;
import kg.beeline.bankaccountoauth2.service.account.AccountService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultAccountEndpoint implements AccountEndpoint {

    private final AccountService accountService;
    private final AccountOperationService accountOperationService;
    private final AccountMapper accountMapper;
    private final UserContextProvider userContextProvider;

    public DefaultAccountEndpoint(
            AccountService accountService,
            AccountOperationService accountOperationService,
            AccountMapper accountMapper,
            UserContextProvider userContextProvider
    ) {
        this.accountService = accountService;
        this.accountOperationService = accountOperationService;
        this.accountMapper = accountMapper;
        this.userContextProvider = userContextProvider;
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDto getUserAccount()
            throws NotFoundException, UnAuthorizedException
    {
        Authentication authentication = userContextProvider.getCurrentAccountPrincipal();
        Account account = accountService.getByUserUid(authentication.getName());
        return accountMapper.toAccountDto(account);
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

    @Override
    @Transactional
    public AccountDto cashOut(AccountOperationRequestDto requestDto)
            throws NotFoundException, UnAuthorizedException, OperationNotValidException
    {
        AccountOperationRequest externalRequest = accountMapper.toAccountOperationRequest(requestDto);
        Account account = accountOperationService.cashOut(externalRequest);

        return accountMapper.toAccountDto(account);
    }

    @Override
    @Transactional
    public AccountDto deposit(AccountOperationRequestDto requestDto)
            throws NotFoundException, UnAuthorizedException, OperationNotValidException
    {
        AccountOperationRequest externalRequest = accountMapper.toAccountOperationRequest(requestDto);
        Account account = accountOperationService.deposit(externalRequest);

        return accountMapper.toAccountDto(account);
    }
}
