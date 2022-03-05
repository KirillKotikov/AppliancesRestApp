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
@Table(name = "computer")
public class ComputerEntity extends AbstractApplianceEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "computer")
    private List<ComputerModelEntity> computerModels;
}
