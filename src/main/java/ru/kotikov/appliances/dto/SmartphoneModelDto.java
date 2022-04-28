package ru.kotikov.appliances.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entity.SmartphoneModelEntity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SmartphoneModelDto extends ApplianceModelDto implements Comparable {

    private Integer volumeOfMemory;
    private Integer numbersOfCameras;
    @JsonIgnore
    private ApplianceDto smartphone;

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
        model.setSmartphone(ApplianceDto.toDto(entity.getSmartphone()));
        return model;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}