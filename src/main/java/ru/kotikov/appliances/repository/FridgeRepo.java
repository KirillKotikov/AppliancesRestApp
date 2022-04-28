package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entity.FridgeEntity;

import java.util.List;

public interface FridgeRepo extends JpaRepository<FridgeEntity, Long> {
    FridgeEntity getByNameContainingIgnoreCase(String fridgeName);
    List<FridgeEntity> findByNameContainingIgnoreCase(String fridgeName);
}