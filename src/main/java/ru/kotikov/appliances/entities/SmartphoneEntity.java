package ru.kotikov.appliances.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "smartphone")
public class SmartphoneEntity extends AbstractApplianceEntity{

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "smartphone")
    private List<SmartphoneModelEntity> smartphoneModels;

}
