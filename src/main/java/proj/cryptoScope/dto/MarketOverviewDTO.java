package proj.cryptoScope.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MarketOverviewDTO {
    private String id;
    private String symbol;
    private String name;
    private BigDecimal marketCap;
    private BigDecimal currentPrice;
    private BigDecimal priceChangePercentage24h;
    private BigDecimal volume24h;
    private BigDecimal circulatingSupply;
    private BigDecimal totalSupply;

    public MarketOverviewDTO(String id, String symbol, String name, BigDecimal marketCap,
                             BigDecimal currentPrice, BigDecimal priceChangePercentage24h,
                             BigDecimal volume24h, BigDecimal circulatingSupply, BigDecimal totalSupply) {
        this.id = id;
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