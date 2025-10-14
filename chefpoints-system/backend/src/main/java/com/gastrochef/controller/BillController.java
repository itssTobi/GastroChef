package com.gastrochef.controller;

import com.gastrochef.dto.ApiResponse;
import com.gastrochef.dto.RedeemCodeRequest;
import com.gastrochef.model.Bill;
import com.gastrochef.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BillController {
    private final BillService billService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createBill(@RequestParam Integer points) {
        Bill bill = billService.createBill(points);
        return ResponseEntity.ok(new ApiResponse(true, "Rechnungscode erstellt", bill));
    }

    @PostMapping("/redeem")
    public ResponseEntity<ApiResponse> redeemCode(@RequestBody RedeemCodeRequest request) {
        try {
            Bill bill = billService.redeemCode(request.getCode(), request.getUserId());
            return ResponseEntity.ok(new ApiResponse(true, "Code erfolgreich eingelöst! " + bill.getPoints() + " ChefPoints gutgeschrieben", bill));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage(), null));
        }
    }

    @GetMapping("/validate/{code}")
    public ResponseEntity<ApiResponse> validateCode(@PathVariable String code) {
        boolean isValid = billService.validateCode(code);
        if (isValid) {
            return ResponseEntity.ok(new ApiResponse(true, "Code ist gültig", null));
        } else {
            return ResponseEntity.ok(new ApiResponse(false, "Code ist ungültig oder bereits eingelöst", null));
        }
    }
}
