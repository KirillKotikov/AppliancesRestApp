package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entity.ComputerEntity;

public interface ComputerRepo extends JpaRepository<ComputerEntity, Long> {
    ComputerEntity findByName (String name);


}
