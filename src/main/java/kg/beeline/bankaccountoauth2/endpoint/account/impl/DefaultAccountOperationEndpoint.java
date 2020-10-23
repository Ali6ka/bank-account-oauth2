package kg.beeline.bankaccountoauth2.endpoint.account.impl;

import kg.beeline.bankaccountoauth2.domain.account.Account;
import kg.beeline.bankaccountoauth2.domain.account.request.AccountOperationRequest;
import kg.beeline.bankaccountoauth2.dto.account.AccountDto;
import kg.beeline.bankaccountoauth2.dto.account.request.AccountOperationRequestDto;
import kg.beeline.bankaccountoauth2.endpoint.account.AccountOperationEndpoint;
import kg.beeline.bankaccountoauth2.exception.NotFoundException;
import kg.beeline.bankaccountoauth2.exception.OperationNotValidException;
import kg.beeline.bankaccountoauth2.exception.UnAuthorizedException;
import kg.beeline.bankaccountoauth2.mapper.AccountMapper;
import kg.beeline.bankaccountoauth2.service.user.UserContextProvider;
import kg.beeline.bankaccountoauth2.service.account.AccountOperationService;
import kg.beeline.bankaccountoauth2.service.account.AccountService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultAccountOperationEndpoint implements AccountOperationEndpoint {

    private final AccountMapper accountMapper;
    private final AccountService accountService;
    private final AccountOperationService accountOperationService;
    private final UserContextProvider userContextProvider;

    public DefaultAccountOperationEndpoint(
            AccountMapper accountMapper,
            AccountService accountService, AccountOperationService accountOperationService,
            UserContextProvider userContextProvider
    ) {
        this.accountMapper = accountMapper;
        this.accountService = accountService;
        this.accountOperationService = accountOperationService;
        this.userContextProvider = userContextProvider;
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDto getAccountInfo()
            throws NotFoundException, UnAuthorizedException
    {
        Authentication authentication = userContextProvider.getCurrentAccountPrincipal();
        Account account = accountService.getByUserUid(authentication.getName());
        return accountMapper.toAccountDto(account);
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
