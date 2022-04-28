package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kotikov.appliances.entity.SmartphoneModelEntity;

import java.util.List;

public interface SmartphoneModelRepo extends JpaRepository<SmartphoneModelEntity, Long> {

    List<SmartphoneModelEntity> getByNameContainingIgnoreCase(String name);

    List<SmartphoneModelEntity> getByColorContainingIgnoreCase(String color);

    List<SmartphoneModelEntity> getByPriceGreaterThanAndPriceLessThan(double low, double high);

    @Query(
            value = "SELECT * FROM smartphone_model WHERE " +
                    "UPPER(name) LIKE CONCAT('%', UPPER(CAST(:name AS VARCHAR)),'%') " +
                    "AND (:serialNumber IS NULL " +
                         "OR serial_number = CAST(CAST(:serialNumber AS VARCHAR) AS INTEGER)) " +
                    "AND UPPER(color) LIKE CONCAT('%', UPPER(CAST(:color AS VARCHAR)), '%') " +
                    "AND UPPER(size) LIKE CONCAT('%', UPPER(CAST(:size AS VARCHAR)), '%') " +
                    "AND (:lowPrice IS NULL OR price > CAST(CAST(:lowPrice AS VARCHAR) AS DOUBLE PRECISION)) " +
                    "AND (:highPrice IS NULL OR price < CAST(CAST(:highPrice AS VARCHAR) AS DOUBLE PRECISION)) " +
                    "AND (:volumeOfMemory IS NULL " +
                         "OR volume_of_memory = CAST(CAST(:volumeOfMemory AS VARCHAR) AS INTEGER)) " +
                    "AND (:numberOfCameras IS NULL " +
                         "OR number_of_cameras = CAST(CAST(:numberOfCameras AS VARCHAR) AS INTEGER)) " +
                    "AND (:inStock IS NULL OR in_stock = (CAST(CAST(:inStock AS TEXT) AS BOOLEAN)))",
            nativeQuery = true
    )
    List<SmartphoneModelEntity> getByParams(
            String name, Long serialNumber, String color, String size, Double lowPrice, Double highPrice,
            Integer volumeOfMemory, Integer numberOfCameras, Boolean inStock);
}