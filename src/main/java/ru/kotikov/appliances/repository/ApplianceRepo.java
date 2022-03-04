package ru.kotikov.appliances.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kotikov.appliances.entities.ApplianceEntity;
import ru.kotikov.appliances.entities.TelevisionEntity;

public interface ApplianceRepo extends CrudRepository<ApplianceEntity, Long> {
    ApplianceEntity findByApplianceName(String appliance);
}
