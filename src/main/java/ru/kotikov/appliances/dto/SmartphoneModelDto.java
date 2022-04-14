package ru.kotikov.appliances.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entity.SmartphoneModelEntity;

@Data
@NoArgsConstructor
public class SmartphoneModelDto implements Comparable {

    private Long id;
    private String name;
    private Long serialNumber;
    private String color;
    private String size;
    private Double price;
    private Boolean inStock;
    private Integer volumeOfMemory;
    private Integer numbersOfCameras;
    private ApplianceDto smartphoneDto;

    public static SmartphoneModelDto toModelDto(SmartphoneModelEntity entity) {
        var model = new SmartphoneModelDto();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setSerialNumber(entity.getSerialNumber());
        model.setColor(entity.getColor());
        model.setSize(entity.getSize());
        model.setPrice(entity.getPrice());
        model.setVolumeOfMemory(entity.getVolumeOfMemory());
        model.setNumbersOfCameras(entity.getNumberOfCameras());
        model.setInStock(entity.getInStock());
        model.setSmartphoneDto(ApplianceDto.toDto(entity.getSmartphone()));

        return model;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
