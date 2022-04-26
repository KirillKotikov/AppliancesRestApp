package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entity.ComputerEntity;

import java.util.List;

public interface ComputerRepo extends JpaRepository<ComputerEntity, Long> {
    ComputerEntity getByName(String name);
    List<ComputerEntity> findByName(String name);


}
