package com.gastrochef.service;

import com.gastrochef.model.Bill;
import com.gastrochef.model.User;
import com.gastrochef.Repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;
    private final UserService userService;

    public Bill createBill(Integer points) {
        Bill bill = new Bill();
        bill.setCode(generateUniqueCode());
        bill.setPoints(points);
        bill.setRedeemedBy(null);

        return billRepository.save(bill);
    }

    @Transactional
    public Bill redeemCode(String code, Long userId) {
        Bill bill = billRepository.findByCode(code)
            .orElseThrow(() -> new IllegalArgumentException("Ungültiger Rechnungscode"));

        if (bill.getRedeemed()) {
            throw new IllegalArgumentException("Code wurde bereits eingelöst");
        }

        User user = userService.getUserById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Benutzer nicht gefunden"));

        bill.setRedeemed(true);
        bill.setRedeemedBy(user);
        bill.setRedeemedAt(LocalDateTime.now());
        billRepository.save(bill);

        userService.addPoints(userId, bill.getPoints());

        return bill;
    }

    public boolean validateCode(String code) {
        Optional<Bill> bill = billRepository.findByCode(code);
        return bill.isPresent() && !bill.get().getRedeemed();
    }

    public List<Bill> getUserRedeemedBills(Long userId) {
        return billRepository.findByRedeemedByIdOrderByRedeemedAtDesc(userId);
    }

    private String generateUniqueCode() {
        String code;
        do {
            code = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        } while (billRepository.existsByCode(code));
        return code;
    }

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

}
