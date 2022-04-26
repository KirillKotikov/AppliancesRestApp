package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entity.FridgeEntity;

import java.util.List;

public interface FridgeRepo extends JpaRepository<FridgeEntity, Long> {
    FridgeEntity getByName(String fridgeName);
    List<FridgeEntity> findByName(String fridgeName);
}
