package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entity.HooverEntity;

public interface HooverRepo extends JpaRepository<HooverEntity, Long> {
    HooverEntity findByName(String hooverName);
}


