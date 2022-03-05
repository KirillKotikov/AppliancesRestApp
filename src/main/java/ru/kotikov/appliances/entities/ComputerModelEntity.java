package ru.kotikov.appliances.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "computer_model")
@NoArgsConstructor
public class ComputerModelEntity extends AbstractApplianceModelEntity{

    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private Integer numberOfProcessors;

    @ManyToOne
    @JoinColumn(name = "computerId", nullable = false)
    private ComputerEntity computer;
}
