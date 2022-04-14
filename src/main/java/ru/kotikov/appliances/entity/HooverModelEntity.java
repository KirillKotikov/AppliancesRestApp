package ru.kotikov.appliances.entity;

import lombok.*;
import org.hibernate.Hibernate;
import ru.kotikov.appliances.dto.HooverModelDto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "hoover_model")
public class HooverModelEntity {

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
    private Integer dustContainerVolume;
    @Column(nullable = false)
    private Integer numberOfModes;
    @ManyToOne
    @JoinColumn(name = "hoover_id", nullable = false)
    private HooverEntity hoover;

    public static HooverModelEntity toEntity(HooverModelDto modelDto) {
        var modelEntity = new HooverModelEntity();
        modelEntity.setId(modelDto.getId());
        modelEntity.setName(modelDto.getName());
        modelEntity.setSerialNumber(modelDto.getSerialNumber());
        modelEntity.setColor(modelDto.getColor());
        modelEntity.setSize(modelDto.getSize());
        modelEntity.setPrice(modelDto.getPrice());
        modelEntity.setInStock(modelDto.getInStock());
        modelEntity.setDustContainerVolume(modelDto.getDustContainerVolume());
        modelEntity.setNumberOfModes(modelDto.getNumberOfModes());
        modelEntity.setHoover(HooverEntity.toEntity(modelDto.getHooverDto()));

        return modelEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HooverModelEntity that = (HooverModelEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
