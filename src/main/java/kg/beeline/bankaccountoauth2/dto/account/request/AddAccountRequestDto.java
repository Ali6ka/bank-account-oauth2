package kg.beeline.bankaccountoauth2.dto.account.request;

import lombok.Data;

import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
public class AddAccountRequestDto {

    private Boolean enabled;

    private String userUid;

    @PositiveOrZero
    private BigDecimal initialAmount;

}
