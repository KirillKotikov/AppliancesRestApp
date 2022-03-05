package ru.kotikov.appliances.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "television")
public class TelevisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String televisionName;
    @Column(nullable = false)
    private String producingCountry;
    @Column(nullable = false)
    private String companyManufacturer;
    @Column(nullable = false)
    private Boolean availableOnline;
    @Column(nullable = false)
    private Boolean installmentPlan;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "television")
    private List<TelevisionModelEntity> TelevisionModels;

    @ManyToOne
    @JoinColumn(name = "applianceId", nullable = false)
    private ApplianceEntity appliance;
}
