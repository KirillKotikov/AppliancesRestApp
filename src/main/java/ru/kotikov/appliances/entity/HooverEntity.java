package ru.kotikov.appliances.entity;

import lombok.*;
import org.hibernate.Hibernate;
import ru.kotikov.appliances.dto.ApplianceDto;
import ru.kotikov.appliances.dto.HooverModelDto;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "hoover")
public class HooverEntity extends ApplianceEntity {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hoover")
    @ToString.Exclude
    private List<HooverModelEntity> hooverModels;

    public static HooverEntity toEntity(ApplianceDto dto) {
        var entity = new HooverEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setProducingCountry(dto.getProducingCountry());
        entity.setCompanyManufacturer(dto.getCompanyManufacturer());
        entity.setAvailableOnline(dto.getAvailableOnline());
        entity.setInstallmentPlan(dto.getInstallmentPlan());
        entity.setHooverModels(dto.getModels()
                .stream().map(x -> (HooverModelDto) x)
                .filter(HooverModelDto::getInStock)
                .map(HooverModelEntity::toEntity)
                .sorted((Comparator.comparing(HooverModelEntity::getPrice)))
                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .collect(Collectors.toList()));
        return entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HooverEntity that = (HooverEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}