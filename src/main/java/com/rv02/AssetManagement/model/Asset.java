package com.rv02.AssetManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "Asset")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @Column
    @NotBlank(message = "Asset names should not be empty")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please provide a date.")
    private Date date;

    @Column(name = "condition-notes")
    private String condition;

    @Column(name = "assignment-status")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Status status;

    public Asset(String name, Date date, String condition) {
        this.name = name;
        this.date = date;
        this.condition = condition;
        this.status = Status.AVAILABLE;
    }

    public Asset(String name, Date date) {
        this.name = name;
        this.date = date;
        this.condition = "";
        this.status = Status.AVAILABLE;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}
