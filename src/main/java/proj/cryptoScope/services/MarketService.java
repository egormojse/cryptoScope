package proj.cryptoScope.services;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import proj.cryptoScope.dto.MarketOverviewDTO;
import proj.cryptoScope.models.MarketOverview;
import proj.cryptoScope.repository.MarketOverviewRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarketService {
    private final MarketOverviewRepository marketOverviewRepository;
    private final CoinGeckoClient coinGeckoClient; // Нужно будет реализовать

    @Scheduled(fixedRate = 300000) // Обновление каждые 5 минут
    public void updateMarketData() {
        List<MarketOverview> topCoins = coinGeckoClient.getTop50Coins();
        marketOverviewRepository.saveAll(topCoins);
    }

    public List<MarketOverviewDTO> getTop50Coins() {
        return marketOverviewRepository.findTop50ByOrderByMarketCapDesc()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MarketOverviewDTO convertToDTO(MarketOverview market) {
        return new MarketOverviewDTO(
                market.getTokenId(),
                market.getSymbol(),
                market.getName(),
                market.getMarketCap(),
                market.getCurrentPrice(),
                market.getPriceChangePercentage24h(),
                market.getVolume24h(),
                market.getCirculatingSupply(),
                market.getTotalSupply()
        );
    }
}
