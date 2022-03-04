package ru.kotikov.appliances.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kotikov.appliances.entities.TelevisionEntity;

public interface TelevisionRepo extends CrudRepository<TelevisionEntity, Long> {

    TelevisionEntity findByTelevisionName(String televisionName);
}
