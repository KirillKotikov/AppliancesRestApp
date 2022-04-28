package ru.kotikov.appliances.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public abstract class AbstractApplianceEntity {
    private Long id;
    private String name;
    private String producingCountry;
    private String companyManufacturer;
    private Boolean availableOnline;
    private Boolean installmentPlan;
    @JsonIgnore
    private List<Object> models;
}