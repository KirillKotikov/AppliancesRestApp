package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entities.ComputerModelEntity;

public interface ComputerModelRepo extends JpaRepository<ComputerModelEntity, Long> {
    ComputerModelEntity findByName (String name);
}
