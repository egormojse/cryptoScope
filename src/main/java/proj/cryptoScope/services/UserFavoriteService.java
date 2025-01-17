package proj.cryptoScope.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import proj.cryptoScope.dto.MarketOverviewDTO;
import proj.cryptoScope.models.MarketOverview;
import proj.cryptoScope.models.User;
import proj.cryptoScope.models.UserFavorite;
import proj.cryptoScope.repository.MarketOverviewRepository;
import proj.cryptoScope.repository.UserFavoriteRepository;
import java.util.Optional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserFavoriteService {
    private final UserFavoriteRepository favoriteRepository;
    private final CoinGeckoClient coinGeckoClient;  // Добавим клиент для API

    public void addToFavorites(Optional<User> userOptional, String tokenId) {
        userOptional.ifPresent(user -> {
            if (!favoriteRepository.existsByUserAndTokenId(user, tokenId)) {
                UserFavorite favorite = new UserFavorite();
                favorite.setUser(user);
                favorite.setTokenId(tokenId);
                favoriteRepository.save(favorite);
            }
        });
    }

    @Transactional
    public void removeFromFavorites(User user, String tokenId) {
        favoriteRepository.deleteByUserAndTokenId(user, tokenId);
    }

    public List<MarketOverviewDTO> getUserFavorites(User user) {
        return favoriteRepository.findByUser(user).stream()
                .map(favorite -> coinGeckoClient.getCoinData(favorite.getTokenId()))  // Получаем актуальные данные через API
                .filter(market -> market != null)  // Проверяем, что данные получены
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
