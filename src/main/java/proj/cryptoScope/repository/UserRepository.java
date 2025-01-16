package proj.cryptoScope.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proj.cryptoScope.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
