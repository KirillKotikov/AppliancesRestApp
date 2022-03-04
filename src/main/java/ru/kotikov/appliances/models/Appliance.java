package ru.kotikov.appliances.models;

import ru.kotikov.appliances.entities.ApplianceEntity;
import ru.kotikov.appliances.entities.TelevisionEntity;
import ru.kotikov.appliances.services.ApplianceService;

import java.util.List;
import java.util.stream.Collectors;

public class Appliance {

    private Long id;
    private String applianceName;
    private List<Television> televisions;

    public Appliance() {
    }

    public static Appliance toModel(ApplianceEntity applianceEntity) {
        var model = new Appliance();
        model.setId(applianceEntity.getId());
        model.setApplianceName(applianceEntity.getApplianceName());
        model.setTelevisions(applianceEntity.getTelevisionEntities()
                .stream().map(Television::toModel).collect(Collectors.toList()));
        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplianceName() {
        return applianceName;
    }

    public void setApplianceName(String applianceName) {
        this.applianceName = applianceName;
    }

    public List<Television> getTelevisions() {
        return televisions;
    }

    public void setTelevisions(List<Television> televisions) {
        this.televisions = televisions;
    }
}
