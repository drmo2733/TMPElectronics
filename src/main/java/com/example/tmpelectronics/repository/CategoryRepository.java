package com.example.tmpelectronics.repository;

import com.example.tmpelectronics.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
