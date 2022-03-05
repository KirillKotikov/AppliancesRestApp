package ru.kotikov.appliances.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "appliance")
@Data
@NoArgsConstructor
public class ApplianceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String applianceName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appliance")
    private List<TelevisionEntity> televisionEntities;

}
