package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entities.HooverModelEntity;

public interface HooverModelRepo extends JpaRepository<HooverModelEntity, Long> {
    HooverModelEntity findByName(String hooverModelName);
}
