package proj.cryptoScope.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proj.cryptoScope.models.MarketOverview;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarketOverviewRepository extends JpaRepository<MarketOverview, Long> {
    List<MarketOverview> findTop50ByOrderByMarketCapDesc();
    Optional<MarketOverview> findBySymbol(String symbol);
    Optional<MarketOverview> findByTokenId(String tokenId);
}
