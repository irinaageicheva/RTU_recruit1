package com.ageicheva.asd.repository;

import com.ageicheva.asd.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {
    /*public List<Purchases> findAllByIdAndCreatedBetween(Integer id, Date from, Date to);
    public List<Purchases> findAllByIdAndProductName(Integer id,String ProductName);
    public List<Purchases> findAllByIdAndPrice(Integer id,Integer price);
    public void deleteByIdAndProductName(Integer id,String productName);*/
}
