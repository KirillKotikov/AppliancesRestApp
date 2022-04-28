package ru.kotikov.appliances.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import ru.kotikov.appliances.dto.ComputerModelDto;
import ru.kotikov.appliances.dto.FridgeModelDto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "fridge_model")
public class FridgeModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private Long serialNumber;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private String size;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Boolean inStock;
    @Column(nullable = false)
    private Integer numbersOfDoors;
    @Column(nullable = false)
    private String compressorType;
    @ManyToOne
    @JoinColumn(name = "fridge_id", nullable = false)
    @JsonIgnore
    private FridgeEntity fridge;

    public static FridgeModelEntity toEntity(FridgeModelDto modelDto) {
        var modelEntity = new FridgeModelEntity();
        modelEntity.setId(modelDto.getId());
        modelEntity.setName(modelDto.getName());
        modelEntity.setSerialNumber(modelDto.getSerialNumber());
        modelEntity.setColor(modelDto.getColor());
        modelEntity.setSize(modelDto.getSize());
        modelEntity.setPrice(modelDto.getPrice());
        modelEntity.setInStock(modelDto.getInStock());
        modelEntity.setNumbersOfDoors(modelDto.getNumbersOfDoors());
        modelEntity.setCompressorType(modelDto.getCompressorType());
        modelEntity.setFridge(FridgeEntity.toEntity(modelDto.getFridge()));
        return modelEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FridgeModelEntity that = (FridgeModelEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}