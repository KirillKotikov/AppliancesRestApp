package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kotikov.appliances.entity.HooverModelEntity;

import java.util.List;

public interface HooverModelRepo extends JpaRepository<HooverModelEntity, Long> {

    List<HooverModelEntity> getByNameContainingIgnoreCase(String name);

    List<HooverModelEntity> getByColorContainingIgnoreCase(String color);

    List<HooverModelEntity> getByPriceGreaterThanAndPriceLessThan(double low, double high);

    @Query(
            value = "SELECT * FROM hoover_model WHERE " +
                    "UPPER(name) LIKE CONCAT('%', UPPER(CAST(:name AS VARCHAR)),'%') " +
                    "AND (:serialNumber IS NULL " +
                          "OR serial_number = CAST(CAST(:serialNumber AS VARCHAR) AS INTEGER)) " +
                    "AND UPPER(color) LIKE CONCAT('%', UPPER(CAST(:color AS VARCHAR)), '%') " +
                    "AND UPPER(size) LIKE CONCAT('%', UPPER(CAST(:size AS VARCHAR)), '%') " +
                    "AND (:lowPrice IS NULL OR price > CAST(CAST(:lowPrice AS VARCHAR) AS DOUBLE PRECISION)) " +
                    "AND (:highPrice IS NULL OR price < CAST(CAST(:highPrice AS VARCHAR) AS DOUBLE PRECISION)) " +
                    "AND (:dustContainerVolume IS NULL " +
                         "OR dust_container_volume = CAST(CAST(:dustContainerVolume AS VARCHAR) AS INTEGER)) " +
                    "AND (:numberOfModes IS NULL " +
                         "OR number_of_modes = CAST(CAST(:numberOfModes AS VARCHAR) AS INTEGER)) " +
                    "AND (:inStock IS NULL OR in_stock = (CAST(CAST(:inStock AS TEXT) AS BOOLEAN)))",
            nativeQuery = true
    )
    List<HooverModelEntity> getByParams(
            String name, Long serialNumber, String color, String size, Double lowPrice, Double highPrice,
            Integer dustContainerVolume, Integer numberOfModes, Boolean inStock);

}
