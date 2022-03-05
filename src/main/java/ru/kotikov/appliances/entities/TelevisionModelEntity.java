package ru.kotikov.appliances.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "television_model")
@NoArgsConstructor
public class TelevisionModelEntity extends AbstractApplianceModelEntity{

    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String technology;

    @ManyToOne
    @JoinColumn(name = "televisionId", nullable = false)
    private TelevisionEntity television;
}

