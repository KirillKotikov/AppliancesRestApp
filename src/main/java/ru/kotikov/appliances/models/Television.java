package ru.kotikov.appliances.models;

import ru.kotikov.appliances.entities.TelevisionEntity;
import ru.kotikov.appliances.entities.TelevisionModelEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Television {
    private Long id;
    private String televisionName;
    private String producingCountry;
    private String companyManufacturer;
    private Boolean availableOnline;
    private Boolean installmentPlan;
    private List<TelevisionModel> televisionModels;

    public Television() {
    }

    public static Television toModel(TelevisionEntity televisionEntity) {
        Television model = new Television();
        model.setId(televisionEntity.getId());
        model.setTelevisionName(televisionEntity.getTelevisionName());
        model.setProducingCountry(televisionEntity.getProducingCountry());
        model.setCompanyManufacturer(televisionEntity.getCompanyManufacturer());
        model.setAvailableOnline(televisionEntity.getAvailableOnline());
        model.setInstallmentPlan(televisionEntity.getInstallmentPlan());
        model.setTelevisionModels(televisionEntity.getTelevisionModels()
                 .stream().filter(TelevisionModelEntity::getInStock).map(TelevisionModel::toTelevisionModel).collect(Collectors.toList()));

        return model;
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

    public void setTelevisionName(String televisionName) {
        this.televisionName = televisionName;
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

    public List<TelevisionModel> getTelevisionModels() {
        return televisionModels;
    }

    public void setTelevisionModels(List<TelevisionModel> televisionModels) {
        this.televisionModels = televisionModels;
    }
}
