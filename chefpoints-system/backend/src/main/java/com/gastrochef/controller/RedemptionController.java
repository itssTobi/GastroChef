package com.gastrochef.controller;

import com.gastrochef.dto.ApiResponse;
import com.gastrochef.dto.RedemptionRequest;
import com.gastrochef.dto.RedemptionHistoryDTO;
import com.gastrochef.dto.CombinedHistoryDTO;
import com.gastrochef.model.Bill;
import com.gastrochef.model.RedemptionHistory;
import com.gastrochef.service.BillService;
import com.gastrochef.service.RedemptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/redemptions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RedemptionController {
    private final RedemptionService redemptionService;
    private final BillService billService;

    @PostMapping("/redeem")
    public ResponseEntity<ApiResponse> redeemProduct(@RequestBody RedemptionRequest request) {
        try {
            RedemptionHistory redemption = redemptionService.redeemProduct(
                request.getUserId(),
                request.getProductId()
            );
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Produkt erfolgreich eingelöst", redemption));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse(false, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(false, "Fehler beim Einlösen: " + e.getMessage(), null));
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getUserRedemptionHistory(@PathVariable Long userId) {
        try {
            List<RedemptionHistory> history = redemptionService.getUserRedemptionHistory(userId);
            LocalDateTime now = LocalDateTime.now();
            List<RedemptionHistoryDTO> historyDTOs = history.stream()
                .filter(h -> {
                    // Nur gültige (nicht abgelaufene) Gutscheincodes anzeigen
                    LocalDateTime expDate = h.getExpirationDate();
                    if (expDate == null) {
                        expDate = h.getRedemptionDate().plusWeeks(2);
                    }
                    return !now.isAfter(expDate);
                })
                .map(h -> {
                    // Fallback für alte Einträge ohne expirationDate
                    LocalDateTime expDate = h.getExpirationDate();
                    if (expDate == null) {
                        expDate = h.getRedemptionDate().plusWeeks(2);
                    }
                    boolean isExpired = now.isAfter(expDate);

                    return new RedemptionHistoryDTO(
                        h.getId(),
                        h.getProduct().getName(),
                        h.getPointsDeducted(),
                        h.getRedemptionCode(),
                        h.getRedemptionDate(),
                        expDate,
                        isExpired
                    );
                })
                .collect(Collectors.toList());
            return ResponseEntity.ok(new ApiResponse(true, "Einlösungshistorie abgerufen", historyDTOs));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse(false, "Fehler: " + e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getRedemption(@PathVariable Long id) {
        try {
            RedemptionHistory redemption = redemptionService.getRedemptionById(id);
            return ResponseEntity.ok(new ApiResponse(true, "Einlösung gefunden", redemption));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/combined/{userId}")
    public ResponseEntity<ApiResponse> getCombinedHistory(@PathVariable Long userId) {
        try {
            List<CombinedHistoryDTO> combinedHistory = new ArrayList<>();
            LocalDateTime now = LocalDateTime.now();

            // Eingelöste Rechnungscodes (+ Punkte)
            List<Bill> bills = billService.getUserRedeemedBills(userId);
            for (Bill bill : bills) {
                LocalDateTime redeemedAt = bill.getRedeemedAt();
                // Wenn kein redeemedAt vorhanden ist, wird null übergeben
                // Das Frontend zeigt dann "Datum unbekannt" an
                combinedHistory.add(new CombinedHistoryDTO(
                    bill.getId(),
                    "BILL_REDEMPTION",
                    "Rechnungscode eingelöst",
                    bill.getPoints(), // positive Punkte
                    bill.getCode(),
                    redeemedAt,
                    null,
                    null
                ));
            }

            // Eingelöste Produkte (- Punkte)
            List<RedemptionHistory> redemptions = redemptionService.getUserRedemptionHistory(userId);
            for (RedemptionHistory h : redemptions) {
                LocalDateTime expDate = h.getExpirationDate();
                if (expDate == null) {
                    expDate = h.getRedemptionDate().plusWeeks(2);
                }
                boolean isExpired = now.isAfter(expDate);

                combinedHistory.add(new CombinedHistoryDTO(
                    h.getId(),
                    "PRODUCT_REDEMPTION",
                    h.getProduct().getName(),
                    -h.getPointsDeducted(), // negative Punkte
                    h.getRedemptionCode(),
                    h.getRedemptionDate(),
                    expDate,
                    isExpired
                ));
            }

            // Nach Datum sortieren (neueste zuerst), null-Werte ans Ende
            combinedHistory.sort(Comparator.comparing(
                CombinedHistoryDTO::getDate,
                Comparator.nullsLast(Comparator.reverseOrder())
            ));

            return ResponseEntity.ok(new ApiResponse(true, "Kombinierte Historie abgerufen", combinedHistory));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                .body(new ApiResponse(false, "Fehler: " + e.getMessage(), null));
        }
    }
}