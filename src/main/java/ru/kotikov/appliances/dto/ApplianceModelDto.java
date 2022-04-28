package ru.kotikov.appliances.dto;

import lombok.Data;

@Data
public abstract class ApplianceModelDto {
    private Long id;
    private String name;
    private Long serialNumber;
    private String color;
    private String size;
    private Double price;
    private Boolean inStock;
}