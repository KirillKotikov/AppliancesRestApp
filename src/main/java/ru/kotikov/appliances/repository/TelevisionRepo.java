package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entities.TelevisionEntity;

public interface TelevisionRepo extends JpaRepository<TelevisionEntity, Long> {

    TelevisionEntity findByName(String televisionName);
}
