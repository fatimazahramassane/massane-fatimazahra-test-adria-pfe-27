package fatimazahra.accountservice.repository;

import fatimazahra.accountservice.domain.Account; // Cette ligne est cruciale
import org.springframework.data.jpa.repository.JpaRepository; // Et celle-ci
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}