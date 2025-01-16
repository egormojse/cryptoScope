package proj.cryptoScope.dto;

import lombok.Data;

@Data
public class MarketOverviewDTO {
    private String id;
    private String symbol;
    private String name;
    private double marketCap;
    private double currentPrice;
    private double priceChangePercentage24h;
    private double volume24h;
    private double circulatingSupply;
    private double totalSupply;

    public MarketOverviewDTO(String symbol, String name, double marketCap,
                             double currentPrice, double priceChangePercentage24h,
                             double volume24h, double circulatingSupply, double totalSupply) {
        this.symbol = symbol;
        this.name = name;
        this.marketCap = marketCap;
        this.currentPrice = currentPrice;
        this.priceChangePercentage24h = priceChangePercentage24h;
        this.volume24h = volume24h;
        this.circulatingSupply = circulatingSupply;
        this.totalSupply = totalSupply;
    }
}