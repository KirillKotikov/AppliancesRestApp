package ru.kotikov.appliances.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "television")
public class TelevisionEntity extends AbstractApplianceEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "television")
    private List<TelevisionModelEntity> TelevisionModels;
}
