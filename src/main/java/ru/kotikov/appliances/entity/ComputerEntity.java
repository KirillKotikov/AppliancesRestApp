package ru.kotikov.appliances.entity;

import lombok.*;
import org.hibernate.Hibernate;
import ru.kotikov.appliances.dto.ApplianceDto;
import ru.kotikov.appliances.dto.ComputerModelDto;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "computer")
public class ComputerEntity extends AbstractApplianceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String producingCountry;
    @Column(nullable = false)
    private String companyManufacturer;
    @Column(nullable = false)
    private Boolean availableOnline;
    @Column(nullable = false)
    private Boolean installmentPlan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "computer")
    @ToString.Exclude
    private List<ComputerModelEntity> computerModels;

    public static ComputerEntity toEntity(ApplianceDto dto) {
        var entity = new ComputerEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setProducingCountry(dto.getProducingCountry());
        entity.setCompanyManufacturer(dto.getCompanyManufacturer());
        entity.setAvailableOnline(dto.getAvailableOnline());
        entity.setInstallmentPlan(dto.getInstallmentPlan());
        entity.setComputerModels(dto.getModels()
                .stream().map(x -> (ComputerModelDto) x)
                .filter(ComputerModelDto::getInStock)
                .map(ComputerModelEntity::toEntity)
                .sorted((Comparator.comparing(ComputerModelEntity::getPrice)))
                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .collect(Collectors.toList()));

        return entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ComputerEntity that = (ComputerEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
