package proj.cryptoScope.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proj.cryptoScope.services.CoinGeckoService;

@RestController
@RequestMapping("/api/coingecko")
public class CoinGeckoController {
    private final CoinGeckoService coinGeckoService;

    public CoinGeckoController(CoinGeckoService coinGeckoService) {
        this.coinGeckoService = coinGeckoService;
    }

    @GetMapping("/markets")
    public String getMarketData(@RequestParam String vsCurrency, @RequestParam int perPage, @RequestParam int page) {
        return coinGeckoService.getMarketData(vsCurrency, perPage, page);
    }
}

