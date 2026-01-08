package com.gastrochef.Repository;

import com.gastrochef.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    Optional<Bill> findByCode(String code);
    boolean existsByCode(String code);
    List<Bill> findByRedeemedByIdOrderByRedeemedAtDesc(Long userId);
}
