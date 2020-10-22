package kg.beeline.bankaccountoauth2.controller.account;

import kg.beeline.bankaccountoauth2.dto.account.AccountDto;
import kg.beeline.bankaccountoauth2.dto.account.request.AccountOperationRequestDto;
import kg.beeline.bankaccountoauth2.dto.account.request.AddAccountRequestDto;
import kg.beeline.bankaccountoauth2.endpoint.account.AccountEndpoint;
import kg.beeline.bankaccountoauth2.exception.NotFoundException;
import kg.beeline.bankaccountoauth2.exception.OperationNotValidException;
import kg.beeline.bankaccountoauth2.exception.UnAuthorizedException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountEndpoint accountEndpoint;

    public AccountController(
            AccountEndpoint accountEndpoint
    ) {
        this.accountEndpoint = accountEndpoint;
    }

    @GetMapping
    public AccountDto getCurrentUserAccounts()
            throws NotFoundException, UnAuthorizedException
    {
        return accountEndpoint.getUserAccount();
    }

    @PostMapping
    public AccountDto create(@Valid @RequestBody AddAccountRequestDto requestDto)
            throws UnAuthorizedException
    {
        return accountEndpoint.addAccount(requestDto);
    }

    @PostMapping("/cash-out")
    public AccountDto cashOut(@Valid @RequestBody AccountOperationRequestDto requestDto)
            throws NotFoundException, UnAuthorizedException, OperationNotValidException
    {
        return accountEndpoint.cashOut(requestDto);
    }

    @PostMapping("/deposit")
    public AccountDto deposit(@Valid @RequestBody AccountOperationRequestDto requestDto)
            throws NotFoundException, UnAuthorizedException, OperationNotValidException
    {
        return accountEndpoint.deposit(requestDto);
    }

}
