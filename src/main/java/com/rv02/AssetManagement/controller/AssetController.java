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

@RestController
@RequestMapping(path = "/api/asset")
public class AssetController {

    private AssetService assetService;
    private CategoryService categoryService;
    private EmployeeService employeeService;

    @Autowired
    public AssetController(AssetService assetService, CategoryService categoryService, EmployeeService employeeService) {
        this.assetService = assetService;
        this.categoryService = categoryService;
        this.employeeService = employeeService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Asset> createAsset(@Valid @RequestBody Asset asset,
                                             @RequestParam int category_id) {
        Category category = categoryService.getCategory(category_id)
                .orElseThrow(DataNotFoundException::new);
        return ResponseEntity.ok(assetService.addAsset(asset, category));
    }

    @GetMapping
    public List<Asset> getAssetsList() {
        return assetService.getAssets();
    }

    @GetMapping(path = "/{name}")
    public List<Asset> searchAssetsByName(@PathVariable String name) {
        return assetService.getAssetByName(name);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable int id,
                                             @Valid @RequestBody Asset newAsset) {
        Asset oldAsset = assetService.getAsset(id)
                .orElseThrow(DataNotFoundException::new);
        Asset updated = assetService.getUpdatedAsset(oldAsset, newAsset);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping(path = "/assign/{asset_id}")
    public ResponseEntity<Asset> assignAssetToEmployee(@PathVariable int asset_id,
                                             @RequestParam int employee_id) {
        Employee employee = employeeService.getEmployee(employee_id).orElseThrow(DataNotFoundException::new);
        Asset asset = assetService.getAsset(asset_id).orElseThrow(DataNotFoundException::new);
        Asset assignedAsset = assetService.assignAsset(asset, employee);
        return ResponseEntity.ok(assignedAsset);
    }

    @PatchMapping(path = "/recover/{id}")
    public ResponseEntity<Asset> recoverAssignedAsset(@PathVariable int id) {
        Asset asset = assetService.getAsset(id).orElseThrow(DataNotFoundException::new);
        return ResponseEntity.ok(assetService.recoverAsset(asset));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteAssetById(@PathVariable int id) {
        Asset asset = assetService.getAsset(id).orElseThrow(DataNotFoundException::new);
        assetService.deleteAsset(asset);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
