package ru.kotikov.appliances.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "hoover_model")
public class HooverModelEntity extends AbstractApplianceModelEntity {

    @Column(nullable = false)
    private Integer dustContainerVolume;
    @Column(nullable = false)
    private Integer numberOfModes;

    @ManyToOne
    @JoinColumn(name = "hooverId", nullable = false)
    private HooverEntity hoover;

}
