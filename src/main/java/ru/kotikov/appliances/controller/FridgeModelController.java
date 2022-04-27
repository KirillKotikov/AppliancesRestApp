package ru.kotikov.appliances.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.dto.FridgeModelDto;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.services.ApplianceModelService;

@RestController
@RequestMapping("/fridge-models")
public class FridgeModelController {

    final
    ApplianceModelService fridgeModelService;

    public FridgeModelController(ApplianceModelService fridgeModelService) {
        this.fridgeModelService = fridgeModelService;
    }

    @Operation(summary = "Создать новую модель холодильников")
    @PostMapping
    public ResponseEntity create(@RequestBody FridgeModelDto fridgeModel,
                                 @RequestParam Long fridgeId) {
        try {
            return ResponseEntity.ok(fridgeModelService.create(fridgeModel, fridgeId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Получить список всех моделей холодильника")
    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(fridgeModelService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск модели холодильника по id")
    @GetMapping("/{id}")
    public ResponseEntity searchById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(fridgeModelService.getById(id));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Обновление модели холодильника")
    @PutMapping
    public ResponseEntity update(@RequestBody FridgeModelDto fridgeModel) {
        try {
            fridgeModelService.update(fridgeModel);
            return ResponseEntity.ok().body("Модель холодильника успешно обновлена!");
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
            fridgeModelService.delete(id);
        } catch (ModelNotFoundException e) {
            e.getMessage();
        }
    }

    @Operation(summary = "Поиск модели компьютера по имени")
    @GetMapping("/search-by-name")
    public ResponseEntity searchByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(fridgeModelService.getByName(name));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск модели компьютера по цвету")
    @GetMapping("/search-by-color")
    public ResponseEntity searchByColor(@RequestParam String color) {
        try {
            return ResponseEntity.ok(fridgeModelService.getByColor(color));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск модели компьютера по цене")
    @GetMapping("/search-by-price")
    public ResponseEntity searchByPrice(@RequestParam Double low, Double high) {
        try {
            return ResponseEntity.ok(fridgeModelService.getByPrice(low, high));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск модели холодильника по любым параметрам (можно с пустыми)")
    @GetMapping
    public ResponseEntity getAllWithFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long serialNumber,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) Double lowPrice,
            @RequestParam(required = false) Double highPrice,
            @RequestParam(required = false) Integer numberOfDoors,
            @RequestParam(required = false) String compressorType,
            @RequestParam(required = false) Boolean inStock
    ) {
        try {
            return ResponseEntity.ok(fridgeModelService.getByParams
                    (name, serialNumber, color, size, lowPrice, highPrice, numberOfDoors, compressorType, inStock));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
