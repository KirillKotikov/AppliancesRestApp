package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kotikov.appliances.entity.FridgeModelEntity;
import ru.kotikov.appliances.entity.FridgeModelEntity;

import java.util.List;

public interface FridgeModelRepo extends JpaRepository<FridgeModelEntity, Long> {

    List<FridgeModelEntity> getByNameContainingIgnoreCase(String name);

    List<FridgeModelEntity> getByColorContainingIgnoreCase(String color);

    List<FridgeModelEntity> getByPriceGreaterThanAndPriceLessThan(double low, double high);

    @Query(
            value = "SELECT * FROM fridge_model WHERE " +
                    "UPPER(name) LIKE CONCAT('%', UPPER(CAST(:name AS VARCHAR)),'%') " +
                    "AND (:serialNumber IS NULL " +
                          "OR serial_number = CAST(CAST(:serialNumber AS VARCHAR) AS INTEGER)) " +
                    "AND UPPER(color) LIKE CONCAT('%', UPPER(CAST(:color AS VARCHAR)), '%') " +
                    "AND UPPER(size) LIKE CONCAT('%', UPPER(CAST(:size AS VARCHAR)), '%') " +
                    "AND (:lowPrice IS NULL OR price > CAST(CAST(:lowPrice AS VARCHAR) AS DOUBLE PRECISION)) " +
                    "AND (:highPrice IS NULL OR price < CAST(CAST(:highPrice AS VARCHAR) AS DOUBLE PRECISION)) " +
                    "AND (:numbersOfDoors IS NULL " +
                          "OR numbers_of_doors = CAST(CAST(:numbersOfDoors AS VARCHAR) AS INTEGER)) " +
                    "AND UPPER(compressor_type) LIKE CONCAT('%', UPPER(CAST(:compressorType AS VARCHAR)), '%') " +
                    "AND (:inStock IS NULL OR in_stock = (CAST(CAST(:inStock AS TEXT) AS BOOLEAN)))",
            nativeQuery = true
    )
    List<FridgeModelEntity> getByParams(
            String name, Long serialNumber, String color, String size, Double lowPrice, Double highPrice,
            Integer numbersOfDoors, String compressorType, Boolean inStock);
}