package com.rv02.AssetManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

/**
 * Entity class for Asset table in db
 */
@Entity
@Table(name = "Asset")
public class Asset {

    /**
     * Unique Id with Identity generation
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    /**
     * Name of the asset. Should not be empty
     */
    @Column
    @NotBlank(message = "Asset names should not be empty")
    private String name;

    /**
     * Date of purchase in "yyyy-MM-dd" format
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please provide a date.")
    @Column
    @Past(message = "Purchase date should be in the past")
    private LocalDate date;

    /**
     * Condition notes can be empty
     */
    @Column(name = "condition_notes")
    private String condition = "";

    /**
     * Status of availability of the asset
     * @see {@link Status}
     */
    @Column(name = "assignment_status")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;

    /**
     * Mapped to category to which asset belongs
     * <strong>Foreign Key</strong>
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * When assigned to an employee it maps to it
     * <strong>Foreign Key</strong>
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Asset(String name, LocalDate date, String condition, Category category) {
        this.name = name;
        this.date = date;
        this.condition = condition;
        this.category = category;
    }

    public Asset(int id, String name, LocalDate date, String condition, Status status, Category category, Employee employee) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.condition = condition;
        this.status = status;
        this.category = category;
        this.employee = employee;
    }

    public Asset(String name, LocalDate date, Category category) {
        this.name = name;
        this.date = date;
        this.category = category;
    }

    public Asset() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
