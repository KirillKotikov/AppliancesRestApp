package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entity.ComputerEntity;

import java.util.List;

public interface ComputerRepo extends JpaRepository<ComputerEntity, Long> {
    ComputerEntity getByNameContainingIgnoreCase(String name);
    List<ComputerEntity> findByNameContainingIgnoreCase(String name);
}