package ru.kotikov.appliances.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "hoover_model")
public class HooverModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String hooverModelName;
    @Column(nullable = false, unique = true)
    private Long serialNumber;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private String size;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer dustContainerVolume;
    @Column(nullable = false)
    private Integer numberOfModes;
    @Column(nullable = false)
    private Boolean inStock;

    @ManyToOne
    @JoinColumn(name = "hooverId", nullable = false)
    private HooverEntity hoover;

    public HooverModelEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHooverModelName() {
        return hooverModelName;
    }

    public void setHooverModelName(String hooverModelName) {
        this.hooverModelName = hooverModelName;
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

    public Integer getDustContainerVolume() {
        return dustContainerVolume;
    }

    public void setDustContainerVolume(Integer dustContainerVolume) {
        this.dustContainerVolume = dustContainerVolume;
    }

    public Integer getNumberOfModes() {
        return numberOfModes;
    }

    public void setNumberOfModes(Integer numberOfModes) {
        this.numberOfModes = numberOfModes;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public HooverEntity getHoover() {
        return hoover;
    }

    public void setHoover(HooverEntity hoover) {
        this.hoover = hoover;
    }
}
