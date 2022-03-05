package ru.kotikov.appliances.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "hoover_model")
@Data
@NoArgsConstructor
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

}
