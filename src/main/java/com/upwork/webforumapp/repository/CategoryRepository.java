package com.upwork.webforumapp.repository;

import com.upwork.webforumapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByCategoryName(String categoryName);
    Integer deleteByCategoryName(String categoryName);
}