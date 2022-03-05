package ru.kotikov.appliances.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hoover")
@Data
@NoArgsConstructor
public class HooverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String hooverName;
    @Column(nullable = false)
    private String producingCountry;
    @Column(nullable = false)
    private String companyManufacturer;
    @Column(nullable = false)
    private Boolean availableOnline;
    @Column(nullable = false)
    private Boolean installmentPlan;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hoover")
    private List<HooverModelEntity> hooverModels;

    @ManyToOne
    @JoinColumn(name = "applianceId", nullable = false)
    private ApplianceEntity appliance;

}
