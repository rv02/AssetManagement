package com.rv02.AssetManagement;

import com.rv02.AssetManagement.dao.AssetRepository;
import com.rv02.AssetManagement.model.Asset;
import com.rv02.AssetManagement.model.Category;
import com.rv02.AssetManagement.model.Employee;
import com.rv02.AssetManagement.model.Status;
import com.rv02.AssetManagement.service.AssetService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AssetServiceTest {

    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private AssetService assetService;

    private final Category dummyCategory = new Category(1, "Electronics",
            "All electronics assets under this category");
    private final Employee dummyEmployee = new Employee("Mary", "CEO");

    @Test
    public void saveAssetTest() {
        Asset asset = new Asset("Laptop",
                LocalDate.of(2019,3, 23),
                dummyCategory);
        Mockito.when(assetRepository.save(asset)).thenReturn(asset);
        assertEquals(dummyCategory, assetService.addAsset(asset, dummyCategory).getCategory());
    }

    @Test
    public void getAssetListTest() {
        Mockito.when(assetRepository.findAll()).thenReturn(Stream.of(
                new Asset("Laptop",
                        LocalDate.of(2019,3, 23),
                        dummyCategory)
        ).collect(Collectors.toList()));
        assertEquals(1, assetService.getAssets().size());
    }

    @Test
    public void getAssetByName() {
        Mockito.when(assetRepository.findByNameIgnoreCase("Pen")).thenReturn(Stream.of(
                new Asset("Pen", LocalDate.of(2021, 2, 14),
                dummyCategory)
        ).collect(Collectors.toList()));
        assertEquals(1, assetService.getAssetByName("Pen").size());
    }

    @Test
    public void updateAssetTest() {
        Asset asset = new Asset("Laptop", LocalDate.of(2019,3, 23),
                dummyCategory);
        Mockito.when(assetRepository.save(asset)).thenReturn(asset);
        Asset updated = new Asset("Laptop",
                LocalDate.of(2019,3, 23),
                "Needs software update", dummyCategory);
        assertEquals(updated.getCondition(), assetService.getUpdatedAsset(asset, updated).getCondition());
    }

    @Test
    public void assignTest() {
        Asset asset = new Asset("Laptop",
                LocalDate.of(2019,3, 23),
                dummyCategory);
        Mockito.when(assetRepository.save(asset)).thenReturn(asset);
        assertSame(Status.ASSIGNED, assetService
                .assignAsset(asset, dummyEmployee).getStatus());
        Mockito.verify(assetRepository, Mockito.times(1)).
                save(asset);
    }

    @Test
    public void recoverTest() {
        Asset asset = new Asset(1,"Laptop",
                LocalDate.of(2019,3, 23), "",
                Status.ASSIGNED, dummyCategory, dummyEmployee);
        Mockito.when(assetRepository.save(asset)).thenReturn(asset);
        asset = assetService.recoverAsset(asset);
        assertNull(asset.getEmployee());
        assertSame(asset.getStatus(), Status.RECOVERED);
    }

    @Test
    public void deleteTest() {
        Asset asset = new Asset(1,"Laptop",
                LocalDate.of(2019,3, 23), "",
                Status.RECOVERED, dummyCategory, null);
        assetService.deleteAsset(asset);
        Mockito.verify(assetRepository, Mockito.times(1))
                .deleteById(asset.getId());
    }
}
