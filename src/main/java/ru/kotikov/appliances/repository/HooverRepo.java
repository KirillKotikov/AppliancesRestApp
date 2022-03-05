package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entities.HooverEntity;

public interface HooverRepo extends JpaRepository<HooverEntity, Long> {
    HooverEntity findByHooverName(String hooverName);
}


