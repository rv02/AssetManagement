package com.rv02.AssetManagement.service;

import com.rv02.AssetManagement.dao.AssetRepository;
import com.rv02.AssetManagement.exceptionHandling.AssetAssignedException;
import com.rv02.AssetManagement.exceptionHandling.AssetNotAssignedException;
import com.rv02.AssetManagement.model.Asset;
import com.rv02.AssetManagement.model.Category;
import com.rv02.AssetManagement.model.Employee;
import com.rv02.AssetManagement.model.Status;
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

    public Asset addAsset(Asset asset, Category category) {
        asset.setCategory(category);
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

    public Asset assignAsset(Asset asset, Employee employee) {
        if (asset.getStatus() == Status.ASSIGNED) {
            throw new AssetAssignedException();
        }
        asset.setStatus(Status.ASSIGNED);
        asset.setEmployee(employee);
        return assetRepository.save(asset);
    }

    public Asset recoverAsset(Asset asset) {
        if (asset.getStatus() != Status.ASSIGNED) {
            throw new AssetNotAssignedException();
        }
        asset.setEmployee(null);
        asset.setStatus(Status.RECOVERED);
        return assetRepository.save(asset);
    }

    public void deleteAsset(Asset asset) {
        if (asset.getStatus() == Status.ASSIGNED) {
            throw new AssetAssignedException();
        }
        assetRepository.deleteById(asset.getId());
    }
}
