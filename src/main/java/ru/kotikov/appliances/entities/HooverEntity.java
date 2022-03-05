package ru.kotikov.appliances.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hoover")
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

    public HooverEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHooverName() {
        return hooverName;
    }

    public void setHooverName(String hooverName) {
        this.hooverName = hooverName;
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

    public List<HooverModelEntity> getHooverModels() {
        return hooverModels;
    }

    public void setHooverModels(List<HooverModelEntity> hooverModels) {
        this.hooverModels = hooverModels;
    }

    public ApplianceEntity getAppliance() {
        return appliance;
    }

    public void setAppliance(ApplianceEntity appliance) {
        this.appliance = appliance;
    }
}
