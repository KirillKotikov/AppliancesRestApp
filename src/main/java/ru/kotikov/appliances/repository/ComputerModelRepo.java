package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kotikov.appliances.entity.ComputerModelEntity;

import java.util.List;

@Repository
public interface ComputerModelRepo extends JpaRepository<ComputerModelEntity, Long> {

    List<ComputerModelEntity> getByNameContainingIgnoreCase(String name);

    List<ComputerModelEntity> getByColorContainingIgnoreCase(String color);

    List<ComputerModelEntity> getByPriceGreaterThanAndPriceLessThan(double low, double high);

    @Query(
            value = "SELECT * FROM computer_model WHERE " +
                    "UPPER(name) LIKE CONCAT('%', UPPER(CAST(:name AS VARCHAR)),'%') " +
                    "AND (:serialNumber IS NULL " +
                    "OR serial_number = CAST(CAST(:serialNumber AS VARCHAR) AS INTEGER)) " +
                    "AND UPPER(color) LIKE CONCAT('%', UPPER(CAST(:color AS VARCHAR)), '%') " +
                    "AND UPPER(size) LIKE CONCAT('%', UPPER(CAST(:size AS VARCHAR)), '%') " +
                    "AND (:lowPrice IS NULL OR price > CAST(CAST(:lowPrice AS VARCHAR) AS DOUBLE PRECISION)) " +
                    "AND (:highPrice IS NULL OR price < CAST(CAST(:highPrice AS VARCHAR) AS DOUBLE PRECISION)) " +
                    "AND UPPER(category) LIKE CONCAT('%', UPPER(CAST(:category AS VARCHAR)), '%') " +
                    "AND UPPER(processor_type) LIKE CONCAT('%', UPPER(CAST(:processorType AS VARCHAR)), '%') " +
                    "AND (:inStock IS NULL OR in_stock = (CAST(CAST(:inStock AS TEXT) AS BOOLEAN)))",
            nativeQuery = true
    )
    List<ComputerModelEntity> getByParams(
            String name, Long serialNumber, String color, String size, Double lowPrice, Double highPrice,
            String category, String processorType, Boolean inStock);
}