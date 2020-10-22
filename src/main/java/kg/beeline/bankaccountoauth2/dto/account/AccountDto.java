package kg.beeline.bankaccountoauth2.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Long id;

    private LocalDateTime createdAt;

    private Boolean enabled;

    private String userUid;

    private BigDecimal amount;
}
