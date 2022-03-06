package ru.kotikov.appliances.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entities.FridgeEntity;
import ru.kotikov.appliances.entities.FridgeModelEntity;
import ru.kotikov.appliances.entities.SmartphoneEntity;
import ru.kotikov.appliances.entities.SmartphoneModelEntity;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Smartphone extends AbstractAppliance{

    private List<SmartphoneModel> smartphoneModels;

    public static Smartphone toModel(SmartphoneEntity smartphoneEntity) {
        var model = new Smartphone();
        model.setId(smartphoneEntity.getId());
        model.setName(smartphoneEntity.getName());
        model.setProducingCountry(smartphoneEntity.getProducingCountry());
        model.setCompanyManufacturer(smartphoneEntity.getCompanyManufacturer());
        model.setAvailableOnline(smartphoneEntity.getAvailableOnline());
        model.setInstallmentPlan(smartphoneEntity.getInstallmentPlan());
        model.setSmartphoneModels(smartphoneEntity.getSmartphoneModels()
                .stream().filter(SmartphoneModelEntity::getInStock).map(SmartphoneModel::toModel)
                .sorted((Comparator.comparing(AbstractApplianceModel::getPrice)))
                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .collect(Collectors.toList()));

        return model;
    }

}
