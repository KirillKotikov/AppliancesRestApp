package ru.kotikov.appliances.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "television_model")
public class TelevisionModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String televisionModelName;
    @Column(nullable = false, unique = true)
    private Long serialNumber;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private String size;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String technology;
    @Column(nullable = false)
    private Boolean inStock;

    @ManyToOne
    @JoinColumn(name = "televisionId", nullable = false)
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

    public TelevisionEntity getTelevision() {
        return television;
    }

    public void setTelevision(TelevisionEntity television) {
        this.television = television;
    }
}

