package ru.kotikov.appliances.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "television")
public class TelevisionEntity extends AbstractApplianceEntity{

    @Column(unique = true, nullable = false)
    private String televisionName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "television")
    private List<TelevisionModelEntity> TelevisionModels;

    @ManyToOne
    @JoinColumn(name = "applianceId", nullable = false)
    private ApplianceEntity appliance;
}
