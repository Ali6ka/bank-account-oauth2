package kg.beeline.bankaccountoauth2.controller.account;

import kg.beeline.bankaccountoauth2.dto.account.AccountDto;
import kg.beeline.bankaccountoauth2.dto.account.request.AccountOperationRequestDto;
import kg.beeline.bankaccountoauth2.endpoint.account.AccountOperationEndpoint;
import kg.beeline.bankaccountoauth2.exception.NotFoundException;
import kg.beeline.bankaccountoauth2.exception.OperationNotValidException;
import kg.beeline.bankaccountoauth2.exception.UnAuthorizedException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/accounts/operation")
public class AccountOperationController {

    private final AccountOperationEndpoint operationEndpoint;

    public AccountOperationController(
            AccountOperationEndpoint operationEndpoint
    ) {
        this.operationEndpoint = operationEndpoint;
    }

    @GetMapping("/check-info")
    public AccountDto getCurrentUserAccounts()
            throws NotFoundException, UnAuthorizedException
    {
        return operationEndpoint.getAccountInfo();
    }

    @PostMapping("/cash-out")
    public AccountDto cashOut(@Valid @RequestBody AccountOperationRequestDto requestDto)
            throws NotFoundException, UnAuthorizedException, OperationNotValidException
    {
        return operationEndpoint.cashOut(requestDto);
    }

    @PostMapping("/deposit")
    public AccountDto deposit(@Valid @RequestBody AccountOperationRequestDto requestDto)
            throws NotFoundException, UnAuthorizedException, OperationNotValidException
    {
        return operationEndpoint.deposit(requestDto);
    }
}
