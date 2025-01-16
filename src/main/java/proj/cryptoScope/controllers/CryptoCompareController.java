package proj.cryptoScope.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proj.cryptoScope.services.CryptoCompareService;

@RestController
@RequestMapping("/api/cryptocompare")
public class CryptoCompareController {
    private final CryptoCompareService cryptoCompareService;

    public CryptoCompareController(CryptoCompareService cryptoCompareService) {
        this.cryptoCompareService = cryptoCompareService;
    }

    @GetMapping("/historical")
    public String getHistoricalPrice(@RequestParam String symbol, @RequestParam String currency) {
        return cryptoCompareService.getHistoricalPrice(symbol, currency);
    }
}

