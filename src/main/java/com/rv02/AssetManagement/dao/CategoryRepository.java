package com.rv02.AssetManagement.dao;

import com.rv02.AssetManagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends
        JpaRepository<Category, Integer> {
}
