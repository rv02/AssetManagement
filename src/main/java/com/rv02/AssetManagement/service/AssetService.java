package com.rv02.AssetManagement.service;

import com.rv02.AssetManagement.dao.AssetRepository;
import com.rv02.AssetManagement.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    private AssetRepository assetRepository;

    @Autowired
    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public Asset addAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    public List<Asset> getAssets() {
        return assetRepository.findAll();
    }

    public Optional<Asset> getAsset(int id) {
        return assetRepository.findById(id);
    }

    public Asset getUpdatedAsset(Asset oldAsset, Asset newAsset) {
        oldAsset.setName(newAsset.getName());
        oldAsset.setDate(newAsset.getDate());
        oldAsset.setCondition(newAsset.getCondition());
        return assetRepository.save(oldAsset);
    }

    public List<Asset> getAssetByName(String name) {
        return assetRepository.findByNameIgnoreCase(name);
    }
}
