package proj.cryptoScope.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoinGeckoResponse {
    private String id;
    private String symbol;
    private String name;
    private Double current_price;
    private Double market_cap;
    private Double total_volume;
    private Double circulating_supply;
    private Double total_supply;
    private Double price_change_percentage_24h;
}
