package ru.kotikov.appliances.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "television_model")
public class TelevisionModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String TelevisionModelName;
    private Long serialNumber;
    private String color;
    private String Size;
    private BigDecimal price;
    private String category;
    private String technology;
    private Boolean inStock;

    @ManyToOne
    @JoinColumn(name = "television_id")
    private TelevisionEntity television;

    public TelevisionModelEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelevisionModelName() {
        return TelevisionModelName;
    }

    public void setTelevisionModelName(String televisionModelName) {
        TelevisionModelName = televisionModelName;
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
        return Size;
    }

    public void setSize(String size) {
        Size = size;
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

    public TelevisionEntity getTelevision() {
        return television;
    }

    public void setTelevision(TelevisionEntity television) {
        this.television = television;
    }
}

