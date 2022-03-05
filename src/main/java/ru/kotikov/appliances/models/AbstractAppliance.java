package ru.kotikov.appliances.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class AbstractAppliance {
    private Long id;
    private String televisionName;
    private String producingCountry;
    private String companyManufacturer;
    private Boolean availableOnline;
    private Boolean installmentPlan;
}
