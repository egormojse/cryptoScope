package proj.cryptoScope.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import proj.cryptoScope.dto.MarketOverviewDTO;
import proj.cryptoScope.dto.UserTransactionDTO;
import proj.cryptoScope.models.MarketOverview;
import proj.cryptoScope.models.User;
import proj.cryptoScope.repository.UserRepository;
import proj.cryptoScope.services.CoinGeckoClient;
import proj.cryptoScope.services.UserFavoriteService;
import proj.cryptoScope.services.UserTransactionService;
import proj.cryptoScope.utils.UserPrincipal;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/crypto")
@RequiredArgsConstructor
public class CryptoController {
    private final UserFavoriteService userFavoriteService;
    private final UserTransactionService userTransactionService;
    private final CoinGeckoClient coinGeckoClient;
    private final UserRepository userRepository;

    @GetMapping("/markets/top50")
    public ResponseEntity<List<MarketOverview>> getTop50Coins() {
        return ResponseEntity.ok(coinGeckoClient.getTop50Coins());
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<MarketOverviewDTO>> getFavorites(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Optional<User> userOptional = userRepository.findByUsername(userPrincipal.getUsername());
        System.out.println(userOptional.get().getUsername());

        return userOptional.map(user -> ResponseEntity.ok(userFavoriteService.getUserFavorites(user))).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping("/favorites/{tokenId}")
    public ResponseEntity<Void> addToFavorites(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable String tokenId) {
        String username = userPrincipal.getUsername();
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userFavoriteService.addToFavorites(userOptional, tokenId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/favorites/{tokenId}")
    public ResponseEntity<Void> removeFromFavorites(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable String tokenId) {
        Optional<User> userOptional = userRepository.findByUsername(userPrincipal.getUsername());

        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userFavoriteService.removeFromFavorites(userOptional.get(), tokenId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transactions")
    public ResponseEntity<UserTransactionDTO> addTransaction(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody UserTransactionDTO transactionDTO) {
        Optional<User> userOptional = userRepository.findByUsername(userPrincipal.getUsername());

        return userOptional.map(user -> ResponseEntity.ok(userTransactionService.addTransaction(user, transactionDTO))).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/transactions")
    public ResponseEntity<List<UserTransactionDTO>> getTransactions(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Optional<User> userOptional = userRepository.findByUsername(userPrincipal.getUsername());

        return userOptional.map(user -> ResponseEntity.ok(userTransactionService.getUserTransactions(user))).orElseGet(() -> ResponseEntity.notFound().build());

    }
}