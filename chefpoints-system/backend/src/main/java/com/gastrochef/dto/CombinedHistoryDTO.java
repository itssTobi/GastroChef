package com.gastrochef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CombinedHistoryDTO {
    private Long id;
    private String type; // "BILL_REDEMPTION" or "PRODUCT_REDEMPTION"
    private String description; // Name des Produkts oder "Rechnungscode eingelöst"
    private Integer points; // + für Bill-Einlösung, - für Produkt-Einlösung
    private String code; // Rechnungscode oder Einlösungscode
    private LocalDateTime date;
    private LocalDateTime expirationDate; // nur für Produkt-Einlösungen
    private Boolean expired; // nur für Produkt-Einlösungen
}

