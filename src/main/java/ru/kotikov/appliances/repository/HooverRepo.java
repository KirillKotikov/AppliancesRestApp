package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.appliances.entity.HooverEntity;

import java.util.List;

public interface HooverRepo extends JpaRepository<HooverEntity, Long> {
    HooverEntity getByNameContainingIgnoreCase(String hooverName);
    List<HooverEntity> findByNameContainingIgnoreCase(String hooverName);
}