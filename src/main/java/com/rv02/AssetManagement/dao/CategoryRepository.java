package com.rv02.AssetManagement.dao;

import com.rv02.AssetManagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CategoryRepository interface for general purpose data access to Category entity.
 * <p>The class provides the mechanism for storage, retrieval, search, update and delete operation on objects</p>
 */
@Repository
public interface CategoryRepository extends
        JpaRepository<Category, Integer> {
}
