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

/**
 * The service layer class for Asset.
 */
@Service
public class AssetService {

    /**
     *AssetRepository to access to db through database layer
     *@see {@link AssetRepository}
     */
    private AssetRepository assetRepository;

    /**
     *
     * @param assetRepository
     */
    @Autowired
    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    /**
     * Saves the given asset to db and gives it a category
     * @param asset asset to be added
     * @param category The category to which asset belong
     * @return Returns the asset saved in database
     */
    public Asset addAsset(Asset asset, Category category) {
        asset.setCategory(category);
        return assetRepository.save(asset);
    }

    /**
     * Returns list of assets in the db.
     * @return List of Asset
     */
    public List<Asset> getAssets() {
        return assetRepository.findAll();
    }

    /**
     *
     * @param id Id of the asset that is required
     * @return Returns assets with given id if found
     */
    public Optional<Asset> getAsset(int id) {
        return assetRepository.findById(id);
    }

    /**
     * Returns and Saves oldAsset changing it to updated one
     * @param oldAsset Asset before update
     * @param newAsset Asset after update
     * @return Updated Asset
     */
    public Asset getUpdatedAsset(Asset oldAsset, Asset newAsset) {
        oldAsset.setName(newAsset.getName());
        oldAsset.setDate(newAsset.getDate());
        oldAsset.setCondition(newAsset.getCondition());
        return assetRepository.save(oldAsset);
    }

    /**
     *
     * @param name
     * @return Returns list of asset by given name
     */
    public List<Asset> getAssetByName(String name) {
        return assetRepository.findByNameIgnoreCase(name);
    }

    /**
     * Changes state and employee of asset
     * @param asset The asset to be assigned
     * @param employee The employee to which asset is assigned
     * @return Assigned Asset
     * @throws AssetAssignedException If asset is already assigned
     */
    public Asset assignAsset(Asset asset, Employee employee) {
        if (asset.getStatus() == Status.ASSIGNED) {
            throw new AssetAssignedException();
        }
        asset.setStatus(Status.ASSIGNED);
        asset.setEmployee(employee);
        return assetRepository.save(asset);
    }

    /**
     * Recovers an asset from employee changing its status to recovered
     * @param asset The asset to be recovered
     * @return Recovered Asset
     * @throws AssetNotAssignedException If asset does not belong to any employee
     */
    public Asset recoverAsset(Asset asset) {
        if (asset.getStatus() != Status.ASSIGNED) {
            throw new AssetNotAssignedException();
        }
        asset.setEmployee(null);
        asset.setStatus(Status.RECOVERED);
        return assetRepository.save(asset);
    }

    /**
     * Deletes an asset which is not assigned to any employee
     * @param asset The asset to be deleted
     * @throws AssetAssignedException Cannot delete assigned asset
     */
    public void deleteAsset(Asset asset) {
        if (asset.getStatus() == Status.ASSIGNED) {
            throw new AssetAssignedException();
        }
        assetRepository.deleteById(asset.getId());
    }
}
