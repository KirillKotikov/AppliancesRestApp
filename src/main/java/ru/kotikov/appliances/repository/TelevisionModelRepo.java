package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entity.TelevisionModelEntity;

public interface TelevisionModelRepo extends JpaRepository<TelevisionModelEntity, Long> {
TelevisionModelEntity findByName(String name);
}
