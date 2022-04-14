package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entity.FridgeModelEntity;

public interface FridgeModelRepo extends JpaRepository<FridgeModelEntity, Long> {
    FridgeModelEntity findByName(String fridgeModelName);
}
