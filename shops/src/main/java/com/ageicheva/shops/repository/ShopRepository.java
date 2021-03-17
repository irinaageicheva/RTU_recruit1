package com.ageicheva.shops.repository;

import com.ageicheva.shops.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    boolean existsByName(String name);
}
