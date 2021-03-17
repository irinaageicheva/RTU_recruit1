package com.ageicheva.asd.repository;

import com.ageicheva.asd.entity.CustomerUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CustomerUser, Integer> {

    boolean existsByUserName(String userName);
}
