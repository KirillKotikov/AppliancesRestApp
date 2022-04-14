package ru.kotikov.appliances.entity;

import lombok.*;
import org.hibernate.Hibernate;
import ru.kotikov.appliances.dto.ComputerModelDto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "computer_model")
public class ComputerModelEntity{

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
    private String processorType;
    @ManyToOne
    @JoinColumn(name = "computer_id", nullable = false)
    private ComputerEntity computer;

    public static ComputerModelEntity toEntity(ComputerModelDto modelDto) {
        var modelEntity = new ComputerModelEntity();

        modelEntity.setId(modelDto.getId());
        modelEntity.setName(modelDto.getName());
        modelEntity.setSerialNumber(modelDto.getSerialNumber());
        modelEntity.setColor(modelDto.getColor());
        modelEntity.setSize(modelDto.getSize());
        modelEntity.setPrice(modelDto.getPrice());
        modelEntity.setInStock(modelDto.getInStock());
        modelEntity.setCategory(modelDto.getCategory());
        modelEntity.setProcessorType(modelDto.getProcessorType());
        modelEntity.setComputer(ComputerEntity.toEntity(modelDto.getComputerDto()));

        return modelEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ComputerModelEntity that = (ComputerModelEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}