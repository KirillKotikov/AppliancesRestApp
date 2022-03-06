package ru.kotikov.appliances.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entities.HooverEntity;
import ru.kotikov.appliances.entities.HooverModelEntity;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Hoover extends AbstractAppliance{

        private List<HooverModel> hooverModels;

        public static Hoover toModel(HooverEntity hooverEntity) {
            Hoover model = new Hoover();
            model.setId(hooverEntity.getId());
            model.setName(hooverEntity.getName());
            model.setProducingCountry(hooverEntity.getProducingCountry());
            model.setCompanyManufacturer(hooverEntity.getCompanyManufacturer());
            model.setAvailableOnline(hooverEntity.getAvailableOnline());
            model.setInstallmentPlan(hooverEntity.getInstallmentPlan());
            model.setHooverModels(hooverEntity.getHooverModels()
                    .stream().filter(HooverModelEntity::getInStock).map(HooverModel::toModel)
                    .sorted((Comparator.comparing(AbstractApplianceModel::getPrice)))
                    .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                    .collect(Collectors.toList()));

            return model;
        }

}
