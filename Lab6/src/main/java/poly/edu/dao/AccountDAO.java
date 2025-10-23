package poly.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.entity.Account;

/**
 * Repository interface for the Account entity.
 */
public interface AccountDAO extends JpaRepository<Account, String> {
}
