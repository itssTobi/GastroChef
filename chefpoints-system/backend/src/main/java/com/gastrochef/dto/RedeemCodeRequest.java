package com.gastrochef.dto;

import lombok.Data;

@Data
public class RedeemCodeRequest {
    private String code;
    private Long userId;
}

