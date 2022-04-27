package ru.kotikov.appliances.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.dto.ComputerModelDto;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.services.ApplianceModelService;

@RestController
@RequestMapping("/computer-models")
public class ComputerModelController {

    private final ApplianceModelService computerModelService;

    public ComputerModelController(ApplianceModelService computerModelService) {
        this.computerModelService = computerModelService;
    }

    @Operation(summary = "Создать новую модель компьютера")
    @PostMapping
    public ResponseEntity create(@RequestBody ComputerModelDto computerModelDto,
                                 @RequestParam Long computerId) {
        try {
            return ResponseEntity.ok(computerModelService.create(computerModelDto, computerId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Получить список всех моделей компьютеров")
    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(computerModelService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск модели компьютера по id")
    @GetMapping("/{id}")
    public ResponseEntity searchById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(computerModelService.getById(id));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Обновление модели компьютера")
    @PutMapping
    public ResponseEntity update(@RequestBody ComputerModelDto computerModel) {
        try {
            computerModelService.update(computerModel);
            return ResponseEntity.ok().body("Модель компьютера успешно обновлена!");
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Удаление модели компьютера по id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            computerModelService.delete(id);
        } catch (ModelNotFoundException e) {
            e.getMessage();
        }
    }

    @Operation(summary = "Поиск модели компьютера по имени")
    @GetMapping("/search-by-name")
    public ResponseEntity searchByName(String name) {
        try {
            return ResponseEntity.ok(computerModelService.getByName(name));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск модели компьютера по цвету")
    @GetMapping("/search-by-color")
    public ResponseEntity searchByColor(String color) {
        try {
            return ResponseEntity.ok(computerModelService.getByColor(color));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск модели компьютера по цене")
    @GetMapping("/search-by-price")
    public ResponseEntity searchByPrice(Double low, Double high) {
        try {
            return ResponseEntity.ok(computerModelService.getByPrice(low, high));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск модели компьютера по любым параметрам (можно с пустыми)")
    @GetMapping
    public ResponseEntity getAllWithFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long serialNumber,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) Double lowPrice,
            @RequestParam(required = false) Double highPrice,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String processorType,
            @RequestParam(required = false) Boolean inStock
    ) {
        try {
            return ResponseEntity.ok(computerModelService.getByParams
                    (name, serialNumber, color, size, lowPrice, highPrice, category, processorType, inStock));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
