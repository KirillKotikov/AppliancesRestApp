package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entities.ComputerEntity;

public interface ComputerRepo extends JpaRepository<ComputerEntity, Long> {
    ComputerEntity findByName (String name);
}
