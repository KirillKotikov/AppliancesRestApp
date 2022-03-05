package ru.kotikov.appliances.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entities.ComputerEntity;
import ru.kotikov.appliances.entities.ComputerModelEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Computer extends AbstractAppliance{

    private List<ComputerModel> computerModels;

    public static Computer toModel(ComputerEntity computerEntity) {
        var model = new Computer();
        model.setId(computerEntity.getId());
        model.setName(computerEntity.getName());
        model.setProducingCountry(computerEntity.getProducingCountry());
        model.setCompanyManufacturer(computerEntity.getCompanyManufacturer());
        model.setAvailableOnline(computerEntity.getAvailableOnline());
        model.setInstallmentPlan(computerEntity.getInstallmentPlan());
        model.setComputerModels(computerEntity.getComputerModels()
                .stream().filter(ComputerModelEntity::getInStock).map(ComputerModel::toModel).collect(Collectors.toList()));

        return model;
    }
}
