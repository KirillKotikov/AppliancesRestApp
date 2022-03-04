package ru.kotikov.appliances.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kotikov.appliances.entities.TelevisionModelEntity;

public interface TelevisionModelRepo extends CrudRepository<TelevisionModelEntity, Long> {
}
