package com.example.tmpelectronics.repository;

import com.example.tmpelectronics.entity.Products;
import com.example.tmpelectronics.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Integer> {



}
