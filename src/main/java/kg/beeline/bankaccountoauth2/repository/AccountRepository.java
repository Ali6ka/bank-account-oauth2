package kg.beeline.bankaccountoauth2.repository;

import kg.beeline.bankaccountoauth2.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUserUid(String userUid);
}
