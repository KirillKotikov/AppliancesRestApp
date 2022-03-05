package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entities.SmartphoneEntity;

public interface SmartphoneRepo extends JpaRepository<SmartphoneEntity, Long> {
    SmartphoneEntity findByName (String name);
}
