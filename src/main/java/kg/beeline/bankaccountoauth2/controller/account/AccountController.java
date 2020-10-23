package kg.beeline.bankaccountoauth2.controller.account;

import kg.beeline.bankaccountoauth2.dto.account.AccountDto;
import kg.beeline.bankaccountoauth2.dto.account.request.AddAccountRequestDto;
import kg.beeline.bankaccountoauth2.endpoint.account.AccountEndpoint;
import kg.beeline.bankaccountoauth2.exception.NotFoundException;
import kg.beeline.bankaccountoauth2.exception.UnAuthorizedException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountEndpoint accountEndpoint;

    public AccountController(
            AccountEndpoint accountEndpoint
    ) {
        this.accountEndpoint = accountEndpoint;
    }

    @GetMapping("/search")
    public List<AccountDto> accounts()
            throws NotFoundException
    {
        return accountEndpoint.searchForAllAccounts();
    }

    @PostMapping("/create")
    public AccountDto create(@Valid @RequestBody AddAccountRequestDto requestDto)
            throws UnAuthorizedException
    {
        return accountEndpoint.addAccount(requestDto);
    }
}
