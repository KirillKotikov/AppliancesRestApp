package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kotikov.appliances.entity.TelevisionModelEntity;

import java.util.List;

public interface TelevisionModelRepo extends JpaRepository<TelevisionModelEntity, Long> {

    List<TelevisionModelEntity> getByNameContainingIgnoreCase(String name);

    List<TelevisionModelEntity> getByColorContainingIgnoreCase(String color);

    List<TelevisionModelEntity> getByPriceGreaterThanAndPriceLessThan(double low, double high);

    @Query(
            value = "SELECT * FROM television_model WHERE " +
                    "UPPER(name) LIKE CONCAT('%', UPPER(CAST(:name AS VARCHAR)),'%') " +
                    "AND (:serialNumber IS NULL " +
                          "OR serial_number = CAST(CAST(:serialNumber AS VARCHAR) AS INTEGER)) " +
                    "AND UPPER(color) LIKE CONCAT('%', UPPER(CAST(:color AS VARCHAR)), '%') " +
                    "AND UPPER(size) LIKE CONCAT('%', UPPER(CAST(:size AS VARCHAR)), '%') " +
                    "AND (:lowPrice IS NULL OR price > CAST(CAST(:lowPrice AS VARCHAR) AS DOUBLE PRECISION)) " +
                    "AND (:highPrice IS NULL OR price < CAST(CAST(:highPrice AS VARCHAR) AS DOUBLE PRECISION)) " +
                    "AND UPPER(category) LIKE CONCAT('%', UPPER(CAST(:category AS VARCHAR)), '%') " +
                    "AND UPPER(technology) LIKE CONCAT('%', UPPER(CAST(:technology AS VARCHAR)), '%') " +
                    "AND (:inStock IS NULL OR in_stock = (CAST(CAST(:inStock AS TEXT) AS BOOLEAN)))",
            nativeQuery = true
    )
    List<TelevisionModelEntity> getByParams(
            String name, Long serialNumber, String color, String size,
            Double lowPrice, Double highPrice, String category, String technology, Boolean inStock);

}