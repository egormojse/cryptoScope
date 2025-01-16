package proj.cryptoScope.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CryptoCompareService {
    private final RestTemplate restTemplate;

    @Value("${cryptocompare.api.key}")
    private String apiKey;

    private static final String BASE_URL = "https://min-api.cryptocompare.com";

    public CryptoCompareService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getHistoricalPrice(String symbol, String currency) {
        String url = BASE_URL + "/data/v2/histoday?fsym=" + symbol
                + "&tsym=" + currency
                + "&api_key=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }
}

