package ru.kotikov.appliances.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import ru.kotikov.appliances.dto.TelevisionModelDto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "television_model")
public class TelevisionModelEntity {

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
    private String category;
    @Column(nullable = false)
    private String technology;
    @ManyToOne
    @JoinColumn(name = "television_id", nullable = false)
    @JsonIgnore
    private TelevisionEntity television;

    public static TelevisionModelEntity toEntity(TelevisionModelDto modelDto) {
        var modelEntity = new TelevisionModelEntity();
        modelEntity.setId(modelDto.getId());
        modelEntity.setName(modelDto.getName());
        modelEntity.setSerialNumber(modelDto.getSerialNumber());
        modelEntity.setColor(modelDto.getColor());
        modelEntity.setSize(modelDto.getSize());
        modelEntity.setPrice(modelDto.getPrice());
        modelEntity.setInStock(modelDto.getInStock());
        modelEntity.setCategory(modelDto.getCategory());
        modelEntity.setTechnology(modelDto.getTechnology());
        modelEntity.setTelevision(TelevisionEntity.toEntity(modelDto.getTelevision()));
        return modelEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TelevisionModelEntity that = (TelevisionModelEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}