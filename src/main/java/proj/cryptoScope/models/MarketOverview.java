package proj.cryptoScope.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "market_overview")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketOverview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "token_id", nullable = false)
    private String tokenId;

    @Column(nullable = false, length = 10)
    private String symbol;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "market_cap", precision = 20, scale = 2)
    private BigDecimal marketCap;

    @Column(name = "current_price", precision = 20, scale = 8)
    private BigDecimal currentPrice;

    @Column(name = "price_change_percentage_24h", precision = 10, scale = 2)
    private BigDecimal priceChangePercentage24h;

    @Column(name = "volume_24h", precision = 20, scale = 2)
    private BigDecimal volume24h;

    @Column(name = "circulating_supply", precision = 20, scale = 2)
    private BigDecimal circulatingSupply;

    @Column(name = "total_supply", precision = 20, scale = 2)
    private BigDecimal totalSupply;

    public MarketOverview(String id, String tokenId, String symbol, BigDecimal marketCap, String name, BigDecimal currentPrice, BigDecimal priceChangePercentage24h, BigDecimal circulatingSupply, BigDecimal totalSupply, BigDecimal volume24h) {
        this.id = id;
        this.tokenId = tokenId;
        this.symbol = symbol;
        this.marketCap = marketCap;
        this.name = name;
        this.currentPrice = currentPrice;
        this.priceChangePercentage24h = priceChangePercentage24h;
        this.circulatingSupply = circulatingSupply;
        this.totalSupply = totalSupply;
        this.volume24h = volume24h;
    }

    public MarketOverview(String id, String symbol, String name, BigDecimal marketCap, BigDecimal currentPrice, BigDecimal priceChangePercentage24h, BigDecimal totalVolume, BigDecimal circulatingSupply, BigDecimal bigDecimal) {
        this.id = id;
        this.tokenId = tokenId;
        this.symbol = symbol;
        this.marketCap = marketCap;
        this.name = name;
        this.currentPrice = currentPrice;
        this.priceChangePercentage24h = priceChangePercentage24h;
        this.circulatingSupply = circulatingSupply;
    }
}

