package ru.kotikov.appliances.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entities.TelevisionEntity;
import ru.kotikov.appliances.entities.TelevisionModelEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Television extends AbstractAppliance{

    private List<TelevisionModel> televisionModels;

    public static Television toModel(TelevisionEntity televisionEntity) {
        Television model = new Television();
        model.setId(televisionEntity.getId());
        model.setName(televisionEntity.getName());
        model.setProducingCountry(televisionEntity.getProducingCountry());
        model.setCompanyManufacturer(televisionEntity.getCompanyManufacturer());
        model.setAvailableOnline(televisionEntity.getAvailableOnline());
        model.setInstallmentPlan(televisionEntity.getInstallmentPlan());
        model.setTelevisionModels(televisionEntity.getTelevisionModels()
                 .stream().filter(TelevisionModelEntity::getInStock).map(TelevisionModel::toModel).collect(Collectors.toList()));

        return model;
    }
}
