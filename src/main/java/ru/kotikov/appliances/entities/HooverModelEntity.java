package ru.kotikov.appliances.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@Table(name = "hoover_model")
public class HooverModelEntity extends AbstractApplianceModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private Long serialNumber;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private String size;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Boolean inStock;
    @Column(nullable = false)
    private Integer dustContainerVolume;
    @Column(nullable = false)
    private Integer numberOfModes;

    @ManyToOne
    @JoinColumn(name = "hooverId", nullable = false)
    private HooverEntity hoover;

}
