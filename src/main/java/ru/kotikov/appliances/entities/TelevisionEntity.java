package ru.kotikov.appliances.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("ALL")
@Entity
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

    public TelevisionEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelevisionName() {
        return televisionName;
    }

    public void setTelevisionName(String name) {
        this.televisionName = name;
    }

    public String getProducingCountry() {
        return producingCountry;
    }

    public void setProducingCountry(String producingCountry) {
        this.producingCountry = producingCountry;
    }

    public String getCompanyManufacturer() {
        return companyManufacturer;
    }

    public void setCompanyManufacturer(String companyManufacturer) {
        this.companyManufacturer = companyManufacturer;
    }

    public Boolean getAvailableOnline() {
        return availableOnline;
    }

    public void setAvailableOnline(Boolean availableOnline) {
        this.availableOnline = availableOnline;
    }

    public Boolean getInstallmentPlan() {
        return installmentPlan;
    }

    public void setInstallmentPlan(Boolean installmentPlan) {
        this.installmentPlan = installmentPlan;
    }

    public List<TelevisionModelEntity> getTelevisionModels() {
        return TelevisionModels;
    }

    public void setTelevisionModels(List<TelevisionModelEntity> televisionModels) {
        TelevisionModels = televisionModels;
    }

    public ApplianceEntity getAppliance() {
        return appliance;
    }

    public void setAppliance(ApplianceEntity appliance) {
        this.appliance = appliance;
    }
}
