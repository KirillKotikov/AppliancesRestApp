package ru.kotikov.appliances.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "computer_model")
@NoArgsConstructor
public class ComputerModelEntity extends AbstractApplianceModelEntity{

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
    private Double price;
    @Column(nullable = false)
    private Boolean inStock;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String processorType;

    @ManyToOne
    @JoinColumn(name = "computerId", nullable = false)
    private ComputerEntity computer;
}
