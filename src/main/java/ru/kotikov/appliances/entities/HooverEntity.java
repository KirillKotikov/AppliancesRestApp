package ru.kotikov.appliances.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "hoover")
public class HooverEntity extends AbstractApplianceEntity{

    @Column(unique = true, nullable = false)
    private String hooverName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hoover")
    private List<HooverModelEntity> hooverModels;

    @ManyToOne
    @JoinColumn(name = "applianceId", nullable = false)
    private ApplianceEntity appliance;

}
