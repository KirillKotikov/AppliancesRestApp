package ru.kotikov.appliances.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.entities.ComputerModelEntity;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.services.ComputerModelService;

@RestController
@RequestMapping("/computer-models")
public class ComputerModelController {

    @Autowired
    ComputerModelService computerModelService;

    @PostMapping // добавляет новую модель в базу данных
    public ResponseEntity create(@RequestBody ComputerModelEntity computerModelEntity,
                                 @RequestParam Long computerId) {
        try {
            return ResponseEntity.ok(computerModelService.create(computerModelEntity, computerId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/get-all") // отображает все модели
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(computerModelService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/{id}") // поиск модели по id
    public ResponseEntity searchByOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(computerModelService.getOne(id));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping // обновление атрибутов модели в базе данных
    public ResponseEntity update(@RequestBody ComputerModelEntity computerModelEntity) {
        try {
            computerModelService.update(computerModelEntity);
            return ResponseEntity.ok().body("Модель компьютера успешно обновлена!");
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}") // удаление модели из базы данных
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(computerModelService.delete(id));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/search-by-name") // поиск модели о имени
    public ResponseEntity searchByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(computerModelService.searchByName(name));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/search-by-color") // поиск модели по цвету
    public ResponseEntity searchByColor(@RequestParam String color) {
        try {
            return ResponseEntity.ok(computerModelService.searchByColor(color));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/search-by-price") // поиск модели по цене
    public ResponseEntity searchByPrice(@RequestParam Double low, Double high) {
        try {
            return ResponseEntity.ok(computerModelService.searchByPrice(low, high));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping // отображает модели с заданными фильтрами
    public ResponseEntity getAllWithFilters(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "0") Long serialNumber,
            @RequestParam(required = false, defaultValue = "") String color,
            @RequestParam(required = false, defaultValue = "") String size,
            @RequestParam(required = false, defaultValue = "0") Double lowPrice,
            @RequestParam(required = false, defaultValue = "20000000") Double highPrice,
            @RequestParam(required = false, defaultValue = "") String category,
            @RequestParam(required = false, defaultValue = "") String processorType,
            @RequestParam(required = false, defaultValue = "true") Boolean inStock
    ) {
        try {
            return ResponseEntity.ok(computerModelService.searchWithFilters
                    (name, serialNumber, color, size, lowPrice, highPrice, category, processorType, inStock));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
