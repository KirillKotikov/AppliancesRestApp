package ru.kotikov.appliances.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entities.ApplianceEntity;
import ru.kotikov.appliances.entities.TelevisionEntity;
import ru.kotikov.appliances.services.ApplianceService;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Appliance {

    private Long id;
    private String applianceName;
    private List<Television> televisions;

    public static Appliance toModel(ApplianceEntity applianceEntity) {
        var model = new Appliance();
        model.setId(applianceEntity.getId());
        model.setApplianceName(applianceEntity.getApplianceName());
        model.setTelevisions(applianceEntity.getTelevisionEntities()
                .stream().map(Television::toModel).collect(Collectors.toList()));
        return model;
    }
}
