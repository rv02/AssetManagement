package com.rv02.AssetManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity classs for table Category
 */
@Entity
@Table(name = "Category")
public class Category {

    /**
     * Unique Id with Identity generation
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    /**
     * Name of category. not to be left empty
     */
    @Column
    @NotBlank(message = "Category names should not be empty")
    private String name;

    /**
     * Contains description of the category
     */
    @Column
    @NotBlank
    private String description;

    /**
     * Mapped to assets that belong to this category
     * JsonBackReference to avoid infinite loop when getting json
     */
    @JsonBackReference
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Asset> assetList = new ArrayList<>();

    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Asset> getAssetList() {
        return assetList;
    }

    public void setAssetList(List<Asset> assetList) {
        this.assetList = assetList;
    }
}
