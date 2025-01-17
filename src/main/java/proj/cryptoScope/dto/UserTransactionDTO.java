package proj.cryptoScope.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserTransactionDTO {
    private Long id;
    private String tokenSymbol;
    private BigDecimal amount;
    private BigDecimal priceAtTransaction;
    private String transactionType;
    private String transactionDate;

    public UserTransactionDTO(Long id, String tokenSymbol, BigDecimal amount,
                              BigDecimal priceAtTransaction, String transactionType, String transactionDate) {
        this.id = id;
        this.tokenSymbol = tokenSymbol;
        this.amount = amount;
        this.priceAtTransaction = priceAtTransaction;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }
}
