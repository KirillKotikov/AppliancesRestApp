package ru.kotikov.appliances.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import ru.kotikov.appliances.dto.SmartphoneModelDto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "smartphone_model")
public class SmartphoneModelEntity {

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
    private Integer volumeOfMemory;
    @Column(nullable = false)
    private Integer numberOfCameras;
    @ManyToOne
    @JoinColumn(name = "smartphone_id", nullable = false)
    @JsonIgnore
    private SmartphoneEntity smartphone;

    public static SmartphoneModelEntity toEntity(SmartphoneModelDto modelDto) {
        var modelEntity = new SmartphoneModelEntity();
        modelEntity.setId(modelDto.getId());
        modelEntity.setName(modelDto.getName());
        modelEntity.setSerialNumber(modelDto.getSerialNumber());
        modelEntity.setColor(modelDto.getColor());
        modelEntity.setSize(modelDto.getSize());
        modelEntity.setPrice(modelDto.getPrice());
        modelEntity.setInStock(modelDto.getInStock());
        modelEntity.setVolumeOfMemory(modelDto.getVolumeOfMemory());
        modelEntity.setNumberOfCameras(modelDto.getNumbersOfCameras());
        modelEntity.setSmartphone(SmartphoneEntity.toEntity(modelDto.getSmartphone()));
        return modelEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SmartphoneModelEntity that = (SmartphoneModelEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}