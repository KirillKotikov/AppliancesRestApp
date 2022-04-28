package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entity.SmartphoneEntity;

import java.util.List;

public interface SmartphoneRepo extends JpaRepository<SmartphoneEntity, Long> {
    SmartphoneEntity getByNameContainingIgnoreCase(String name);
    List<SmartphoneEntity> findByNameContainingIgnoreCase(String name);
}