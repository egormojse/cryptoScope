package proj.cryptoScope.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CoinMarketData {
    private String id;
    private String symbol;
    private String name;

    @JsonProperty("current_price")
    private BigDecimal currentPrice;

    @JsonProperty("market_cap")
    private BigDecimal marketCap;

    @JsonProperty("price_change_percentage_24h")
    private BigDecimal priceChangePercentage24h;

    @JsonProperty("total_volume")
    private BigDecimal volume24h;

    @JsonProperty("circulating_supply")
    private BigDecimal circulatingSupply;

    @JsonProperty("total_supply")
    private BigDecimal totalSupply;
}