package com.rv02.AssetManagement.controller;

import com.rv02.AssetManagement.exceptionHandling.DataNotFoundException;
import com.rv02.AssetManagement.model.Asset;
import com.rv02.AssetManagement.model.Category;
import com.rv02.AssetManagement.model.Employee;
import com.rv02.AssetManagement.service.AssetService;
import com.rv02.AssetManagement.service.CategoryService;
import com.rv02.AssetManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The Controller class for Asset Management. Provides the rest api endpoints and functionalities for the same.
 * Asset endpoint : /api/asset
 */
@RestController
@RequestMapping(path = "/api/asset")
public class AssetController {

    /**
     * AssetService to connect to Asset service layer
     */
    private AssetService assetService;
    /**
     * CategoryService to connect to Category service layer
     */
    private CategoryService categoryService;
    /**
     * EmployeeService to connect to Employee service layer
     */
    private EmployeeService employeeService;

    /**
     * Provides Service class beans.
     * @param assetService
     * @param categoryService
     * @param employeeService
     */
    @Autowired
    public AssetController(AssetService assetService, CategoryService categoryService, EmployeeService employeeService) {
        this.assetService = assetService;
        this.categoryService = categoryService;
        this.employeeService = employeeService;
    }

    /**
     * Api endpoint to add asset
     *
     * @param asset Valid asset to be added
     * @param category_id Category Id of the asset
     * @throws DataNotFoundException Id does not match
     * @return ResponseStatus 200 with body of creted Asset
     */
    @PostMapping(consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Asset> createAsset(@Valid @RequestBody Asset asset,
                                             @RequestParam int category_id) {
        Category category = categoryService.getCategory(category_id)
                .orElseThrow(DataNotFoundException::new);
        return ResponseEntity.ok(assetService.addAsset(asset, category));
    }

    /**
     *Lists all the assets
     * @return Asset List
     */
    @GetMapping
    public List<Asset> getAssetsList() {
        return assetService.getAssets();
    }

    /**
     * Searches asset by the given name
     * @param name Name of asset to search
     * @return List of assets with given name
     */
    @GetMapping(path = "/{name}")
    public List<Asset> searchAssetsByName(@PathVariable String name) {
        return assetService.getAssetByName(name);
    }

    /**
     *
     * @param id Id of asset to be updated
     * @param newAsset Valid Body with updated Asset
     * @return 200 HttpStatus with updated asset
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable int id,
                                             @Valid @RequestBody Asset newAsset) {
        Asset oldAsset = assetService.getAsset(id)
                .orElseThrow(DataNotFoundException::new);
        Asset updated = assetService.getUpdatedAsset(oldAsset, newAsset);
        return ResponseEntity.ok(updated);
    }

    /**
     * If not assigned, Changes status of assets to Assigned and maps to employee
     * @param asset_id Asset to be asssigned
     * @param employee_id The employee who gets the asset
     * @throws com.rv02.AssetManagement.exceptionHandling.AssetAssignedException when asset is already assigned
     * @return 200 HttpStatus
     */
    @PatchMapping(path = "/assign/{asset_id}")
    public ResponseEntity<Asset> assignAssetToEmployee(@PathVariable int asset_id,
                                             @RequestParam int employee_id) {
        Employee employee = employeeService.getEmployee(employee_id).orElseThrow(DataNotFoundException::new);
        Asset asset = assetService.getAsset(asset_id).orElseThrow(DataNotFoundException::new);
        Asset assignedAsset = assetService.assignAsset(asset, employee);
        return ResponseEntity.ok(assignedAsset);
    }

    /**
     * Changes status of Assigned assets to Recovered and removes mappping to employee
     * @param id Asset id to be recovered
     * @throws com.rv02.AssetManagement.exceptionHandling.AssetNotAssignedException Cannot recover unassigned asset
     * @return
     */
    @PatchMapping(path = "/recover/{id}")
    public ResponseEntity<Asset> recoverAssignedAsset(@PathVariable int id) {
        Asset asset = assetService.getAsset(id).orElseThrow(DataNotFoundException::new);
        return ResponseEntity.ok(assetService.recoverAsset(asset));
    }

    /**
     * Deletes an asset if not Assigned
     * @param id Asset id to be deleted
     * @throws com.rv02.AssetManagement.exceptionHandling.AssetAssignedException Cannot delete assigned asset exception
     * @return
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteAssetById(@PathVariable int id) {
        Asset asset = assetService.getAsset(id).orElseThrow(DataNotFoundException::new);
        assetService.deleteAsset(asset);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
