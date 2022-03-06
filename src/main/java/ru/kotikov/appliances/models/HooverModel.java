package ru.kotikov.appliances.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entities.HooverModelEntity;

@Data
@NoArgsConstructor
public class HooverModel extends AbstractApplianceModel{

    private Integer dustContainerVolume;
    private Integer numberOfModes;

    public static HooverModel toModel(HooverModelEntity entity) {
        var model = new HooverModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setSerialNumber(entity.getSerialNumber());
        model.setColor(entity.getColor());
        model.setSize(entity.getSize());
        model.setPrice(entity.getPrice());
        model.setDustContainerVolume(entity.getDustContainerVolume());
        model.setNumberOfModes(entity.getNumberOfModes());
        model.setInStock(entity.getInStock());

        return model;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
