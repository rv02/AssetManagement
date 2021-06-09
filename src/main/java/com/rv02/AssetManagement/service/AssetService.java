package com.rv02.AssetManagement.service;

import com.rv02.AssetManagement.dao.AssetRepository;
import com.rv02.AssetManagement.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    private AssetRepository assetRepository;

    @Autowired
    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public void addAsset(Asset asset) {
        assetRepository.save(asset);
    }

    public List<Asset> getAssets() {
        return assetRepository.findAll();
    }
}
