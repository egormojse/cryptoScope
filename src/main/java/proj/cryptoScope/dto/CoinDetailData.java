package proj.cryptoScope.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CoinDetailData {
    private String id;
    private String symbol;
    private String name;

    @JsonProperty("market_data")
    private MarketData marketData;

    @Data
    public static class MarketData {
        @JsonProperty("current_price")
        private PriceData currentPrice;

        @JsonProperty("market_cap")
        private PriceData marketCap;

        @JsonProperty("total_volume")
        private PriceData totalVolume;

        @JsonProperty("price_change_percentage_24h")
        private Double priceChangePercentage24h;

        @JsonProperty("circulating_supply")
        private Double circulatingSupply;

        @JsonProperty("total_supply")
        private Double totalSupply;
    }

    @Data
    public static class PriceData {
        private Double usd;
        private Double eur;
        private Double btc;
    }
}
