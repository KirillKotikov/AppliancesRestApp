package ru.kotikov.appliances.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entities.TelevisionModelEntity;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TelevisionModel extends AbstractApplianceModel{

    private String category;
    private String technology;

    public static TelevisionModel toTelevisionModel(TelevisionModelEntity entity) {
        var televisionModel = new TelevisionModel();
        televisionModel.setId(entity.getId());
        televisionModel.setTelevisionModelName(entity.getTelevisionModelName());
        televisionModel.setSerialNumber(entity.getSerialNumber());
        televisionModel.setColor(entity.getColor());
        televisionModel.setSize(entity.getSize());
        televisionModel.setPrice(entity.getPrice());
        televisionModel.setCategory(entity.getCategory());
        televisionModel.setTechnology(entity.getTechnology());
        televisionModel.setInStock(entity.getInStock());

        return televisionModel;
    }
}
