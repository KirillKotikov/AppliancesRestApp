package ru.kotikov.appliances.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entities.TelevisionModelEntity;

@Data
@NoArgsConstructor
public class TelevisionModel extends AbstractApplianceModel{

    private String category;
    private String technology;

    public static TelevisionModel toTelevisionModel(TelevisionModelEntity entity) {
        var model = new TelevisionModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setSerialNumber(entity.getSerialNumber());
        model.setColor(entity.getColor());
        model.setSize(entity.getSize());
        model.setPrice(entity.getPrice());
        model.setCategory(entity.getCategory());
        model.setTechnology(entity.getTechnology());
        model.setInStock(entity.getInStock());

        return model;
    }
}
