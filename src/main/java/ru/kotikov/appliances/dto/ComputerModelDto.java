package ru.kotikov.appliances.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entity.ComputerModelEntity;

@Data
@NoArgsConstructor
public class ComputerModelDto implements Comparable {

    private Long id;
    private String name;
    private Long serialNumber;
    private String color;
    private String size;
    private Double price;
    private Boolean inStock;
    private String category;
    private String processorType;
    private ApplianceDto computerDto;

    public static ComputerModelDto toModelDto(ComputerModelEntity entity) {
        var model = new ComputerModelDto();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setSerialNumber(entity.getSerialNumber());
        model.setColor(entity.getColor());
        model.setSize(entity.getSize());
        model.setPrice(entity.getPrice());
        model.setCategory(entity.getCategory());
        model.setProcessorType(entity.getProcessorType());
        model.setInStock(entity.getInStock());
        model.setComputerDto(ApplianceDto.toDto(entity.getComputer()));

        return model;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}