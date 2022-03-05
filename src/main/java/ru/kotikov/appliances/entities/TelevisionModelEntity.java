package ru.kotikov.appliances.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "television_model")
@NoArgsConstructor
public class TelevisionModelEntity extends AbstractApplianceModelEntity{

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
    private String category;
    @Column(nullable = false)
    private String technology;


    @ManyToOne
    @JoinColumn(name = "televisionId", nullable = false)
    private TelevisionEntity television;
}

