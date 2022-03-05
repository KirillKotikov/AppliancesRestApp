package ru.kotikov.appliances.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entities.FridgeEntity;
import ru.kotikov.appliances.entities.FridgeModelEntity;
import ru.kotikov.appliances.entities.HooverEntity;
import ru.kotikov.appliances.entities.HooverModelEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Fridge extends AbstractAppliance{

    private List<FridgeModel> frudgeModels;

    public static Fridge toModel(FridgeEntity fridgeEntity) {
        var model = new Fridge();
        model.setId(fridgeEntity.getId());
        model.setName(fridgeEntity.getName());
        model.setProducingCountry(fridgeEntity.getProducingCountry());
        model.setCompanyManufacturer(fridgeEntity.getCompanyManufacturer());
        model.setAvailableOnline(fridgeEntity.getAvailableOnline());
        model.setInstallmentPlan(fridgeEntity.getInstallmentPlan());
        model.setFrudgeModels(fridgeEntity.getFridgeModels()
                .stream().filter(FridgeModelEntity::getInStock).map(FridgeModel::toModel).collect(Collectors.toList()));

        return model;
    }

}
