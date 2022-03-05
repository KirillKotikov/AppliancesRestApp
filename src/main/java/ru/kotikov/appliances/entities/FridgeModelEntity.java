package ru.kotikov.appliances.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "hoover_model")
public class FridgeModelEntity extends AbstractApplianceModelEntity {

    @Column(nullable = false)
    private Integer numbersOfDoors;
    @Column(nullable = false)
    private String compressorType;

    @ManyToOne
    @JoinColumn(name = "fridgeId", nullable = false)
    private FridgeEntity fridge;

}
