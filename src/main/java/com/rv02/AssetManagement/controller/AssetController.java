package com.rv02.AssetManagement.controller;

import com.rv02.AssetManagement.model.Asset;
import com.rv02.AssetManagement.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/asset")
public class AssetController {

    private AssetService assetService;

    @Autowired
    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Asset> createAsset(@Valid @RequestBody Asset asset) {
        return ResponseEntity.ok(assetService.addAsset(asset));
    }

    @GetMapping
    public List<Asset> getAssetsList() {
        return assetService.getAssets();
    }
}
