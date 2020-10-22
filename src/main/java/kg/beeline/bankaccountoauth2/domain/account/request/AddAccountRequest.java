package kg.beeline.bankaccountoauth2.domain.account.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddAccountRequest {

    private Boolean enabled;

    private String userUid;

    private BigDecimal initialAmount;

}
