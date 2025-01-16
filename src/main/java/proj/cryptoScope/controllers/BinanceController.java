package proj.cryptoScope.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proj.cryptoScope.services.BinanceService;

@RestController
@RequestMapping("/api/binance")
public class BinanceController {
    private final BinanceService binanceService;

    public BinanceController(BinanceService binanceService) {
        this.binanceService = binanceService;
    }

    @GetMapping("/ticker-price")
    public String getTickerPrice(@RequestParam String symbol) {
        return binanceService.getTickerPrice(symbol);
    }
}

