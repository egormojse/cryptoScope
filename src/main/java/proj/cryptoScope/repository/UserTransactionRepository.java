package proj.cryptoScope.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proj.cryptoScope.models.User;
import proj.cryptoScope.models.UserTransaction;

import java.util.List;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction, Long> {
    List<UserTransaction> findByUserOrderByTransactionDateDesc(User user);
    List<UserTransaction> findByUserAndTokenSymbol(User user, String tokenSymbol);
}