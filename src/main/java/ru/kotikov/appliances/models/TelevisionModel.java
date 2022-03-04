package ru.kotikov.appliances.models;

import ru.kotikov.appliances.entities.TelevisionModelEntity;

import java.math.BigDecimal;

public class TelevisionModel {

    private Long id;
    private String televisionModelName;
    private Long serialNumber;
    private String color;
    private String size;
    private BigDecimal price;
    private String category;
    private String technology;
    private Boolean inStock;

    public TelevisionModel() {
    }

    public static TelevisionModel toTelevisionModel(TelevisionModelEntity entity) {
        var televisionModel = new TelevisionModel();
        televisionModel.setId(entity.getId());
        televisionModel.setTelevisionModelName(entity.getTelevisionModelName());
        televisionModel.setSerialNumber(entity.getSerialNumber());
        televisionModel.setColor(entity.getColor());
        televisionModel.setSize(entity.getSize());
        televisionModel.setPrice(entity.getPrice());
        televisionModel.setCategory(entity.getCategory());
        televisionModel.setTechnology(entity.getTechnology());
        televisionModel.setInStock(entity.getInStock());

        return televisionModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelevisionModelName() {
        return televisionModelName;
    }

    public void setTelevisionModelName(String televisionModelName) {
        this.televisionModelName = televisionModelName;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }
}
