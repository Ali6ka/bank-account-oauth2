package kg.beeline.bankaccountoauth2.service.account.impl;

import kg.beeline.bankaccountoauth2.domain.account.Account;
import kg.beeline.bankaccountoauth2.domain.account.request.AccountOperationRequest;
import kg.beeline.bankaccountoauth2.exception.NotFoundException;
import kg.beeline.bankaccountoauth2.exception.OperationNotValidException;
import kg.beeline.bankaccountoauth2.repository.AccountRepository;
import kg.beeline.bankaccountoauth2.service.account.AccountOperationService;
import kg.beeline.bankaccountoauth2.service.account.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class DefaultAccountOperationService implements AccountOperationService {

    private final AccountService accountService;
    private final AccountRepository repository;

    public DefaultAccountOperationService(
            AccountService accountService,
            AccountRepository repository
    ) {
        this.accountService = accountService;
        this.repository = repository;
    }

    @Override
    @Transactional
    public Account cashOut(AccountOperationRequest externalRequest)
            throws NotFoundException, OperationNotValidException
    {
        Account account = checkUserAccount(externalRequest.getUserUid());
        BigDecimal remainAmount = account.getAmount().subtract(externalRequest.getAmount());
        account.setAmount(remainAmount);

        return repository.save(account);
    }

    @Override
    @Transactional
    public Account deposit(AccountOperationRequest externalRequest)
            throws NotFoundException, OperationNotValidException
    {
        Account account = checkUserAccount(externalRequest.getUserUid());
        BigDecimal remainAmount = account.getAmount().add(externalRequest.getAmount());
        account.setAmount(remainAmount);

        return repository.save(account);
    }

    private Account checkUserAccount(String userUid)
            throws NotFoundException, OperationNotValidException
    {
        Account account = accountService.getByUserUid(userUid);

        if(!account.getEnabled()){
            throw new OperationNotValidException("Невозможно выполнить действие. Карта заблокирована");
        }

        return account;
    }
}
