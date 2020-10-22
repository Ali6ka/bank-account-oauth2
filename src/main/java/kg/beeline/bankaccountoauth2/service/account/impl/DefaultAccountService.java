package kg.beeline.bankaccountoauth2.service.account.impl;

import kg.beeline.bankaccountoauth2.domain.account.Account;
import kg.beeline.bankaccountoauth2.domain.account.request.AddAccountRequest;
import kg.beeline.bankaccountoauth2.exception.NotFoundException;
import kg.beeline.bankaccountoauth2.repository.AccountRepository;
import kg.beeline.bankaccountoauth2.service.account.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultAccountService implements AccountService {

    private final AccountRepository repository;

    public DefaultAccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Account get(Long accountId) throws NotFoundException {
        return repository.findById(accountId)
                .orElseThrow(() -> new NotFoundException(Account.class, accountId, "id"));
    }

    @Override
    @Transactional(readOnly = true)
    public Account getByUserUid(String userUid) throws NotFoundException {
        return repository.findByUserUid(userUid)
                .orElseThrow(() -> new NotFoundException(Account.class, userUid, "userUid"));
    }

    @Override
    @Transactional
    public Account create(Account account) {
        return repository.save(account);
    }

    @Override
    @Transactional
    public Account create(AddAccountRequest requestDto) {
        Account account = new Account();
        account.setEnabled(requestDto.getEnabled());
        account.setAmount(requestDto.getInitialAmount());
        account.setUserUid(requestDto.getUserUid());

        return repository.save(account);
    }
}
