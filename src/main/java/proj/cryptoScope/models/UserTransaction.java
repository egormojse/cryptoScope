package proj.cryptoScope.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import proj.cryptoScope.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "token_symbol", nullable = false, length = 10)
    private String tokenSymbol;

    @Column(nullable = false, precision = 20, scale = 8)
    private BigDecimal amount;

    @Column(name = "price_at_transaction", precision = 20, scale = 8)
    private BigDecimal priceAtTransaction;

    @Column(name = "transaction_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;
}