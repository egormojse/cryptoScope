package proj.cryptoScope.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proj.cryptoScope.models.User;
import proj.cryptoScope.models.UserFavorite;

import java.util.List;

@Repository
public interface UserFavoriteRepository extends JpaRepository<UserFavorite, Long> {
    boolean existsByUserAndTokenId(User user, String tokenId);
    void deleteByUserAndTokenId(User user, String tokenId);
    List<UserFavorite> findByUser(User user);
}