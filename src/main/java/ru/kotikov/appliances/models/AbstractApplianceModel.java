package ru.kotikov.appliances.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public abstract class AbstractApplianceModel {
    private Long id;
    private String televisionModelName;
    private Long serialNumber;
    private String color;
    private String size;
    private BigDecimal price;
    private Boolean inStock;
}
