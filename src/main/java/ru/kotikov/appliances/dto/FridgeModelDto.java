package ru.kotikov.appliances.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entity.FridgeModelEntity;

@Data
@NoArgsConstructor
public class FridgeModelDto implements Comparable {

    private Long id;
    private String name;
    private Long serialNumber;
    private String color;
    private String size;
    private Double price;
    private Boolean inStock;
    private Integer numbersOfDoors;
    private String compressorType;
    private ApplianceDto fridgeDto;

    public static FridgeModelDto toModelDto(FridgeModelEntity entity) {
        var model = new FridgeModelDto();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setSerialNumber(entity.getSerialNumber());
        model.setColor(entity.getColor());
        model.setSize(entity.getSize());
        model.setPrice(entity.getPrice());
        model.setNumbersOfDoors(entity.getNumbersOfDoors());
        model.setCompressorType(entity.getCompressorType());
        model.setInStock(entity.getInStock());
        model.setFridgeDto(ApplianceDto.toDto(entity.getFridge()));

        return model;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
