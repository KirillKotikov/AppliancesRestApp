package ru.kotikov.appliances.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "appliance")
public class ApplianceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applianceName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appliance")
    private List<TelevisionEntity> televisionEntities;

    public ApplianceEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplianceName() {
        return applianceName;
    }

    public void setApplianceName(String applianceName) {
        this.applianceName = applianceName;
    }

    public List<TelevisionEntity> getTelevisionEntities() {
        return televisionEntities;
    }

    public void setTelevisionEntities(List<TelevisionEntity> televisionEntities) {
        this.televisionEntities = televisionEntities;
    }
}
