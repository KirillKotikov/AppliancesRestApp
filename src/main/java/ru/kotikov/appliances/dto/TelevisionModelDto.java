package ru.kotikov.appliances.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entity.TelevisionModelEntity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TelevisionModelDto extends ApplianceModelDto implements Comparable {

    private String category;
    private String technology;
    private ApplianceDto televisionDto;

    public static TelevisionModelDto toModelDto(TelevisionModelEntity entity) {
        var model = new TelevisionModelDto();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setSerialNumber(entity.getSerialNumber());
        model.setColor(entity.getColor());
        model.setSize(entity.getSize());
        model.setPrice(entity.getPrice());
        model.setCategory(entity.getCategory());
        model.setTechnology(entity.getTechnology());
        model.setInStock(entity.getInStock());
        model.setTelevisionDto(ApplianceDto.toDto(entity.getTelevision()));

        return model;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
