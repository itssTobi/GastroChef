package com.gastrochef.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private Integer points;

    @Column(nullable = false)
    private Boolean redeemed = false;

    @ManyToOne
    @JoinColumn(name = "redeemed_by_user_id")
    private User redeemedBy;
}
