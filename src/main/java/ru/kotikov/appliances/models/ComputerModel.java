package ru.kotikov.appliances.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entities.ComputerModelEntity;

@Data
@NoArgsConstructor
public class ComputerModel extends AbstractApplianceModel{

    private String category;
    private String processorType;

    public static ComputerModel toModel(ComputerModelEntity entity) {
        var model = new ComputerModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setSerialNumber(entity.getSerialNumber());
        model.setColor(entity.getColor());
        model.setSize(entity.getSize());
        model.setPrice(entity.getPrice());
        model.setCategory(entity.getCategory());
        model.setProcessorType(entity.getProcessorType());
        model.setInStock(entity.getInStock());

        return model;
    }
}