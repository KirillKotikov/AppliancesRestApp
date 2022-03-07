package ru.kotikov.appliances.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public abstract class AbstractApplianceModel implements Comparable {
    private Long id;
    private String name;
    private Long serialNumber;
    private String color;
    private String size;
    private Double price;
    private Boolean inStock;
}
