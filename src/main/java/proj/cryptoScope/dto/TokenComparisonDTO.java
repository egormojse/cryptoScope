package proj.cryptoScope.dto;

import lombok.Data;

@Data
public class TokenComparisonDTO {
    private String symbol;
    private String name;
    private double price;
    private double marketCap;
    private double volume24h;
    private double circulatingSupply;
    private double priceChangePercentage1h;
    private double priceChangePercentage24h;
    private double priceChangePercentage7d;

    public TokenComparisonDTO(String symbol, String name, double price,
                              double marketCap, double volume24h, double circulatingSupply,
                              double priceChangePercentage24h, double priceChangePercentage7d,
                              double priceChangePercentage1h) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.marketCap = marketCap;
        this.volume24h = volume24h;
        this.circulatingSupply = circulatingSupply;
        this.priceChangePercentage24h = priceChangePercentage24h;
        this.priceChangePercentage7d = priceChangePercentage7d;
        this.priceChangePercentage1h = priceChangePercentage1h;
    }
}