package proj.cryptoScope.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoinGeckoService {
    private final RestTemplate restTemplate;

    private static final String BASE_URL = "https://api.coingecko.com/api/v3";

    public CoinGeckoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getMarketData(String vsCurrency, int perPage, int page) {
        String url = BASE_URL + "/coins/markets?vs_currency=" + vsCurrency
                + "&order=market_cap_desc&per_page=" + perPage
                + "&page=" + page;
        return restTemplate.getForObject(url, String.class);
    }
}
