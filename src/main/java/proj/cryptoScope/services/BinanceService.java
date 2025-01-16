package proj.cryptoScope.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BinanceService {
    private final RestTemplate restTemplate;

    private static final String BASE_URL = "https://api.binance.com";

    public BinanceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getTickerPrice(String symbol) {
        String url = BASE_URL + "/api/v3/ticker/price?symbol=" + symbol;
        return restTemplate.getForObject(url, String.class);
    }
}
