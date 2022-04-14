package ru.kotikov.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kotikov.appliances.entity.ComputerModelEntity;

import java.util.List;

@Repository
public interface ComputerModelRepo extends JpaRepository<ComputerModelEntity, Long> {

    @Query(
            "FROM ComputerModelEntity AS c WHERE " +
                    ":name IS NULL OR UPPER(c.name) LIKE CONCAT('%', :name,'%')"
    )
    List<ComputerModelEntity> getByNameContainingIgnoreCase(@Param("name") String name);

    List<ComputerModelEntity> getByColorContainingIgnoreCase(String color);

    List<ComputerModelEntity> getByPriceGreaterThanAndPriceLessThan(double low, double high);

    @Query(
            "FROM ComputerModelEntity AS c WHERE " +
                    ":name IS NULL OR UPPER(c.name) LIKE CONCAT('%', :name,'%') " +
                    "AND :serialNumber IS NULL OR c.serialNumber = :serialNumber " +
                    "AND :color IS NULL OR UPPER(c.color) LIKE CONCAT('%', :color, '%') " +
                    "AND :size IS NULL OR UPPER(c.size) LIKE CONCAT('%', :size, '%') " +
                    "AND (:lowPrice IS NULL AND :highPrice IS NULL) OR (c.price > :lowPrice AND c.price < :highPrice) " +
                    "AND :category IS NULL OR UPPER(c.category) LIKE CONCAT('%', :category, '%') " +
                    "AND :processorType IS NULL OR UPPER(c.processorType) LIKE CONCAT('%', :processorType, '%') " +
                    "AND :inStock IS NULL OR c.inStock = :inStock"
    )
    List<ComputerModelEntity> getByParams(
            @Param("name") String name, @Param("serialNumber") Long serialNumber, @Param("color") String color,
            @Param("size") String size, @Param("lowPrice") Double lowPrice, @Param("highPrice") Double highPrice,
            @Param("category") String category, @Param("processorType") String processorType, @Param("inStock") Boolean inStock);
}
