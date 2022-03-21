package com.example.tmpelectronics.repository;

import com.example.tmpelectronics.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}
