package proj.cryptoScope.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import proj.cryptoScope.dto.CoinGeckoResponse;
import proj.cryptoScope.dto.CoinMarketData;
import proj.cryptoScope.models.MarketOverview;

import java.math.BigDecimal;
import java.util.*;

@Component
@RequiredArgsConstructor
public class CoinGeckoClient {
    private final RestTemplate restTemplate;

    @Value("${coingecko.api.url}")
    private String baseUrl;

    private static final String API_BASE_URL = "https://api.coingecko.com/api/v3";
    private final ObjectMapper objectMapper;

    public List<MarketOverview> getTop50Coins() {
        String url = API_BASE_URL + "/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=50&page=1";

        try {
            String response = restTemplate.getForObject(url, String.class);
            List<Map<String, Object>> coins = objectMapper.readValue(response, new TypeReference<>() {});

            List<MarketOverview> marketOverviews = new ArrayList<>();
            for (Map<String, Object> coin : coins) {
                marketOverviews.add(parseMarketOverview(coin));
            }
            return marketOverviews;

        } catch (Exception e) {
            throw new RuntimeException("Error fetching data from CoinGecko API: " + e.getMessage(), e);
        }
    }

    private MarketOverview parseMarketOverview(Map<String, Object> coin) {
        return new MarketOverview(
                (String) coin.get("id"),
                (String) coin.get("symbol"),
                (String) coin.get("name"),
                new BigDecimal(coin.get("market_cap").toString()),
                new BigDecimal(coin.get("current_price").toString()),
                new BigDecimal(coin.get("price_change_percentage_24h").toString()),
                new BigDecimal(coin.get("total_volume").toString()),
                new BigDecimal(coin.get("circulating_supply").toString()),
                coin.get("total_supply") != null ? new BigDecimal(coin.get("total_supply").toString()) : null
        );
    }

    private MarketOverview convertToMarketOverview(CoinMarketData data) {
        MarketOverview overview = new MarketOverview();
        overview.setTokenId(data.getId());
        overview.setSymbol(data.getSymbol());
        overview.setName(data.getName());
        overview.setMarketCap(data.getMarketCap());
        overview.setCurrentPrice(data.getCurrentPrice());
        overview.setPriceChangePercentage24h(data.getPriceChangePercentage24h());
        overview.setVolume24h(data.getVolume24h());
        overview.setCirculatingSupply(data.getCirculatingSupply());
        overview.setTotalSupply(data.getTotalSupply());
        return overview;
    }


    public MarketOverview getCoinData(String tokenId) {
        String url = baseUrl + "/coins/markets" +
                "?vs_currency=usd" +
                "&ids=" + tokenId +
                "&order=market_cap_desc" +
                "&per_page=1" +
                "&page=1" +
                "&sparkline=false";

        try {
            ResponseEntity<List<CoinGeckoResponse>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CoinGeckoResponse>>() {}
            );

            if (response.getBody() != null && !response.getBody().isEmpty()) {
                CoinGeckoResponse coinData = response.getBody().get(0);
                return convertToMarketOverview(coinData);
            }
            return null;
        } catch (Exception e) {
            System.out.println("Error fetching coin data for " + tokenId);
            return null;
        }
    }

    private MarketOverview convertToMarketOverview(CoinGeckoResponse response) {
        MarketOverview overview = new MarketOverview();
        overview.setTokenId(response.getId());
        overview.setSymbol(response.getSymbol());
        overview.setName(response.getName());
        overview.setMarketCap(BigDecimal.valueOf(response.getMarket_cap()));
        overview.setCurrentPrice(BigDecimal.valueOf(response.getCurrent_price()));
        overview.setPriceChangePercentage24h(BigDecimal.valueOf(response.getPrice_change_percentage_24h()));
        overview.setVolume24h(BigDecimal.valueOf(response.getTotal_volume()));
        overview.setCirculatingSupply(BigDecimal.valueOf(response.getCirculating_supply()));
        overview.setTotalSupply(BigDecimal.valueOf(response.getTotal_supply()));
        return overview;
    }
}