package proj.cryptoScope.dto;

import lombok.Data;

@Data
public class CurrencyConversionDTO {
    private String fromCurrency;
    private String toCurrency;
    private double amount;
    private double convertedAmount;

    public CurrencyConversionDTO(String fromCurrency, String toCurrency, double amount,
                                 double convertedAmount) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
    }
}
