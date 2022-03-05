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
@Table(name = "fridge")
public class FridgeEntity extends AbstractApplianceEntity{

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fridge")
    private List<FridgeModelEntity> fridgeModels;

}
