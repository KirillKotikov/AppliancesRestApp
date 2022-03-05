package ru.kotikov.appliances.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@Table(name = "fridge_model")
public class FridgeModelEntity extends AbstractApplianceModelEntity {

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
    private Integer numbersOfDoors;
    @Column(nullable = false)
    private String compressorType;

    @ManyToOne
    @JoinColumn(name = "fridgeId", nullable = false)
    private FridgeEntity fridge;

}
