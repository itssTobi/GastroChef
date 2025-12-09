package com.gastrochef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedemptionHistoryDTO {
    private Long id;
    private String productName;
    private Integer pointsDeducted;
    private String redemptionCode;
    private LocalDateTime redemptionDate;
}

