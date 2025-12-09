package com.gastrochef.Repository;

import com.gastrochef.model.RedemptionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RedemptionRepository extends JpaRepository<RedemptionHistory, Long> {
    List<RedemptionHistory> findByUserId(Long userId);
}

