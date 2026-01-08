package com.gastrochef.service;

import com.gastrochef.model.RedemptionHistory;
import com.gastrochef.model.User;
import com.gastrochef.model.Product;
import com.gastrochef.Repository.RedemptionRepository;
import com.gastrochef.Repository.UserRepository;
import com.gastrochef.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RedemptionService {
    private final RedemptionRepository redemptionRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public RedemptionHistory redeemProduct(Long userId, Long productId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Benutzer nicht gefunden"));

        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new IllegalArgumentException("Produkt nicht gefunden"));

        if (!product.getActive()) {
            throw new IllegalArgumentException("Dieses Produkt ist nicht verfügbar");
        }

        Integer pointsCost = product.getPointsCost();
        Integer userPoints = user.getChefPoints();

        if (userPoints < pointsCost) {
            throw new IllegalArgumentException(
                "Nicht genügend Punkte. Sie haben " + userPoints +
                " Punkte, benötigen aber " + pointsCost
            );
        }

        // Punkte abziehen
        user.setChefPoints(userPoints - pointsCost);
        userRepository.save(user);

        // Einlösungscode generieren
        String redemptionCode = generateRedemptionCode();

        // Einlösungsdatum und Ablaufdatum (2 Wochen)
        LocalDateTime now = LocalDateTime.now();
        // Normal ist der Code 2 Wochen lang gültig zum Testen auf eine Minute gesetzt
        // LocalDateTime expirationDate = now.plusWeeks(2);
       LocalDateTime expirationDate = now.plusMinutes(1);

        // Historie speichern
        RedemptionHistory history = new RedemptionHistory();
        history.setUser(user);
        history.setProduct(product);
        history.setPointsDeducted(pointsCost);
        history.setRedemptionCode(redemptionCode);
        history.setRedemptionDate(now);
        history.setExpirationDate(expirationDate);

        return redemptionRepository.save(history);
    }

    public List<RedemptionHistory> getUserRedemptionHistory(Long userId) {
        return redemptionRepository.findByUserIdOrderByRedemptionDateDesc(userId);
    }

    public RedemptionHistory getRedemptionById(Long id) {
        return redemptionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Einlösung nicht gefunden"));
    }

    private String generateRedemptionCode() {
        // Format: GC-XXXXXXXXXX (GastroChef + 10 Zufallszeichen)
        return "GC-" + UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
    }
}

