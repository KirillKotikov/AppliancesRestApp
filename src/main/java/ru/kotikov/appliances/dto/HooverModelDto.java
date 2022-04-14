package ru.kotikov.appliances.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entity.HooverModelEntity;

@Data
@NoArgsConstructor
public class HooverModelDto implements Comparable {

    private Long id;
    private String name;
    private Long serialNumber;
    private String color;
    private String size;
    private Double price;
    private Boolean inStock;
    private Integer dustContainerVolume;
    private Integer numberOfModes;
    private ApplianceDto hooverDto;

    public static HooverModelDto toModelDto(HooverModelEntity entity) {
        var model = new HooverModelDto();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setSerialNumber(entity.getSerialNumber());
        model.setColor(entity.getColor());
        model.setSize(entity.getSize());
        model.setPrice(entity.getPrice());
        model.setDustContainerVolume(entity.getDustContainerVolume());
        model.setNumberOfModes(entity.getNumberOfModes());
        model.setInStock(entity.getInStock());
        model.setHooverDto(ApplianceDto.toDto(entity.getHoover()));
        return model;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
