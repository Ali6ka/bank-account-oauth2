package kg.beeline.bankaccountoauth2.domain.account;

import com.sun.istack.NotNull;
import kg.beeline.bankaccountoauth2.domain.common.TimedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account extends TimedEntity {

    @NotNull
    @Column(name = "enabled")
    private Boolean enabled;

    @NotNull
    @Column(name = "user_uid")
    private String userUid;

    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;
}
