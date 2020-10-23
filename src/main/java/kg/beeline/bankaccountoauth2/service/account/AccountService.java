package kg.beeline.bankaccountoauth2.service.account;

import kg.beeline.bankaccountoauth2.domain.account.Account;
import kg.beeline.bankaccountoauth2.domain.account.request.AddAccountRequest;
import kg.beeline.bankaccountoauth2.exception.NotFoundException;

import java.util.List;

public interface AccountService {
    List<Account> getAll() throws NotFoundException;

    Account get(Long accountId) throws NotFoundException;

    Account getByUserUid(String userUid) throws NotFoundException;

    Account create(Account account);

    Account create(AddAccountRequest addAccountRequestDto);
}
