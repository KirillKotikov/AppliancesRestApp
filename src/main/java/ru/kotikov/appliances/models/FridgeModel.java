package ru.kotikov.appliances.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entities.FridgeModelEntity;

@Data
@NoArgsConstructor
public class FridgeModel extends AbstractApplianceModel {

    private Integer numbersOfDoors;
    private String compressorType;

    public static FridgeModel toModel(FridgeModelEntity entity) {
        var model = new FridgeModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setSerialNumber(entity.getSerialNumber());
        model.setColor(entity.getColor());
        model.setSize(entity.getSize());
        model.setPrice(entity.getPrice());
        model.setNumbersOfDoors(entity.getNumbersOfDoors());
        model.setCompressorType(entity.getCompressorType());
        model.setInStock(entity.getInStock());

        return model;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
