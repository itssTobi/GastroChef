package com.gastrochef.controller;

import com.gastrochef.dto.ApiResponse;
import com.gastrochef.dto.RedemptionRequest;
import com.gastrochef.dto.RedemptionHistoryDTO;
import com.gastrochef.model.RedemptionHistory;
import com.gastrochef.service.RedemptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/redemptions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RedemptionController {
    private final RedemptionService redemptionService;

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
            List<RedemptionHistoryDTO> historyDTOs = history.stream()
                .map(h -> new RedemptionHistoryDTO(
                    h.getId(),
                    h.getProduct().getName(),
                    h.getPointsDeducted(),
                    h.getRedemptionCode(),
                    h.getRedemptionDate()
                ))
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
}

