package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entities.ApplianceEntity;

public interface ApplianceRepo extends JpaRepository<ApplianceEntity, Long> {
    ApplianceEntity findByApplianceName(String appliance);
}
