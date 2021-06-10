package com.rv02.AssetManagement.dao;

import com.rv02.AssetManagement.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AssetRepository interface for general purpose data access and communication to Access entity.
 * <p>
 * The class provides the mechanism for storage, retrieval, search, update and delete operation on objects
 *</p>
 */
@Repository
public interface AssetRepository extends
        JpaRepository<Asset, Integer> {
    List<Asset> findByNameIgnoreCase(String name);
}
