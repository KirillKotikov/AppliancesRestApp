package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entity.TelevisionEntity;

import java.util.List;

public interface TelevisionRepo extends JpaRepository<TelevisionEntity, Long> {

    TelevisionEntity getByNameContainingIgnoreCase(String name);
    List<TelevisionEntity> findByNameContainingIgnoreCase(String name);
}