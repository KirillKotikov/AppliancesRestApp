package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entity.TelevisionEntity;

import java.util.List;

public interface TelevisionRepo extends JpaRepository<TelevisionEntity, Long> {

    TelevisionEntity getByName(String name);
    List<TelevisionEntity> findByName(String name);
}
