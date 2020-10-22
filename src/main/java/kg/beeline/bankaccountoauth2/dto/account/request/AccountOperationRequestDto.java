package kg.beeline.bankaccountoauth2.dto.account.request;

import lombok.Data;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class AccountOperationRequestDto {

    @Positive
    private BigDecimal amount;
}
