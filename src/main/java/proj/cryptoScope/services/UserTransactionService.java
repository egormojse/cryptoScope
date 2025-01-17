package proj.cryptoScope.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import proj.cryptoScope.dto.UserTransactionDTO;
import proj.cryptoScope.enums.TransactionType;
import proj.cryptoScope.models.MarketOverview;
import proj.cryptoScope.models.User;
import proj.cryptoScope.models.UserTransaction;
import proj.cryptoScope.repository.MarketOverviewRepository;
import proj.cryptoScope.repository.UserTransactionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserTransactionService {
    private final UserTransactionRepository transactionRepository;
    private final CoinGeckoClient coinGeckoClient;

    public UserTransactionDTO addTransaction(User user, UserTransactionDTO dto) {
        // Получаем данные о токене из API
        MarketOverview market = coinGeckoClient.getCoinData(dto.getTokenSymbol());
        if (market == null) {
            throw new RuntimeException("Token data not found in CoinGecko API for symbol: " + dto.getTokenSymbol());
        }

        // Создаем транзакцию
        UserTransaction transaction = new UserTransaction();
        transaction.setUser(user);
        transaction.setTokenSymbol(dto.getTokenSymbol());
        transaction.setAmount(dto.getAmount());
        transaction.setPriceAtTransaction(market.getCurrentPrice());
        transaction.setTransactionType(TransactionType.valueOf(dto.getTransactionType()));
        transaction.setTransactionDate(LocalDateTime.now());

        // Сохраняем транзакцию в БД
        UserTransaction savedTransaction = transactionRepository.save(transaction);
        return convertToDTO(savedTransaction);
    }



    private UserTransactionDTO convertToDTO(UserTransaction transaction) {
        return new UserTransactionDTO(
                transaction.getId(),
                transaction.getTokenSymbol(),
                transaction.getAmount(),
                transaction.getPriceAtTransaction(),
                transaction.getTransactionType().toString(),
                transaction.getTransactionDate().toString()
        );
    }

    public List<UserTransactionDTO> getUserTransactions(User user) {
        return transactionRepository.findByUserOrderByTransactionDateDesc(user)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}
