package ru.kotikov.appliances.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entities.FridgeModelEntity;
import ru.kotikov.appliances.entities.SmartphoneModelEntity;

@Data
@NoArgsConstructor
public class SmartphoneModel extends AbstractApplianceModel {

    private Integer volumeOfMemory;
    private Integer numbersOfCameras;

    public static SmartphoneModel toModel(SmartphoneModelEntity entity) {
        var model = new SmartphoneModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setSerialNumber(entity.getSerialNumber());
        model.setColor(entity.getColor());
        model.setSize(entity.getSize());
        model.setPrice(entity.getPrice());
        model.setVolumeOfMemory(entity.getVolumeOfMemory());
        model.setNumbersOfCameras(entity.getNumberOfCameras());
        model.setInStock(entity.getInStock());

        return model;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
