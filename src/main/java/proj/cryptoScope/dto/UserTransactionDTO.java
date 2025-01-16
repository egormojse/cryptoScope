package proj.cryptoScope.dto;

import lombok.Data;

@Data
public class UserTransactionDTO {
    private Long id;
    private String tokenSymbol;
    private double amount;
    private double priceAtTransaction;
    private String transactionType;
    private String transactionDate;

    public UserTransactionDTO(String tokenSymbol, double amount,
                              double priceAtTransaction, String transactionType, String transactionDate) {
        this.tokenSymbol = tokenSymbol;
        this.amount = amount;
        this.priceAtTransaction = priceAtTransaction;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }
}
