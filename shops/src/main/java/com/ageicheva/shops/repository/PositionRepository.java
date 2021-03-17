package com.ageicheva.shops.repository;

import com.ageicheva.shops.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Integer> {
    List<Position> findAllByShopId(Integer shopId);
    Position findByPositionNameAndShopId(String positionName, Integer shopId);

}
