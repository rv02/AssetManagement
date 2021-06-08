package com.rv02.AssetManagement.dao;

import com.rv02.AssetManagement.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends
        JpaRepository<Asset, Integer> {
}
