package kg.beeline.bankaccountoauth2.service.account;

import kg.beeline.bankaccountoauth2.domain.account.Account;
import kg.beeline.bankaccountoauth2.domain.account.request.AccountOperationRequest;
import kg.beeline.bankaccountoauth2.exception.NotFoundException;
import kg.beeline.bankaccountoauth2.exception.OperationNotValidException;

public interface AccountOperationService {

    Account cashOut(AccountOperationRequest externalRequest) throws NotFoundException, OperationNotValidException;

    Account deposit(AccountOperationRequest externalRequest) throws NotFoundException, OperationNotValidException;
}
