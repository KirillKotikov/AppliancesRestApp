package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entities.SmartphoneModelEntity;

public interface SmartphoneModelRepo extends JpaRepository<SmartphoneModelEntity, Long> {
    SmartphoneModelEntity findByName (String name);
}
