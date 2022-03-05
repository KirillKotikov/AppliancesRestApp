package ru.kotikov.appliances.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "smartphone_model")
public class SmartphoneModelEntity extends AbstractApplianceModelEntity {

    @Column(nullable = false)
    private Integer volumeOfMemory;
    @Column(nullable = false)
    private Integer numberOfCameras;

    @ManyToOne
    @JoinColumn(name = "smartphoneId", nullable = false)
    private SmartphoneEntity smartphone;

}
