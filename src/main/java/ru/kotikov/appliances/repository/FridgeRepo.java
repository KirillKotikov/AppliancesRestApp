package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entities.FridgeEntity;
import ru.kotikov.appliances.entities.FridgeModelEntity;

public interface FridgeRepo extends JpaRepository<FridgeEntity, Long> {
    FridgeEntity findByName(String fridgeName);
}
