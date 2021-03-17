package com.ageicheva.shops.repository;

import com.ageicheva.shops.entity.ReceiptHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptHistoryRepository extends JpaRepository<ReceiptHistory, Integer> {
}
