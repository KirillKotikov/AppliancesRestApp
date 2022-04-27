package ru.kotikov.appliances.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.dto.SmartphoneModelDto;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.services.ApplianceModelService;

@RestController
@RequestMapping("/smartphone-models")
public class SmartphoneModelController {

    final ApplianceModelService smartphoneModelService;

    public SmartphoneModelController(ApplianceModelService smartphoneModelService) {
        this.smartphoneModelService = smartphoneModelService;
    }

    @Operation(summary = "Создать новую модель смартфона")
    @PostMapping
    public ResponseEntity create(@RequestBody SmartphoneModelDto smartphoneModel,
                                 @RequestParam Long smartphoneId) {
        try {
            return ResponseEntity.ok(smartphoneModelService.create(smartphoneModel, smartphoneId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Получить список всех моделей смартфонов")
    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(smartphoneModelService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск модели пылесоса по id")
    @GetMapping("/{id}")
    public ResponseEntity searchById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(smartphoneModelService.getById(id));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Обновление модели пылесоса")
    @PutMapping
    public ResponseEntity update(@RequestBody SmartphoneModelDto smartphoneModel) {
        try {
            smartphoneModelService.update(smartphoneModel);
            return ResponseEntity.ok().body("Модель смартфона успешно обновлена!");
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Удаление модели пылесоса по id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            smartphoneModelService.delete(id);
        } catch (ModelNotFoundException e) {
            e.getMessage();
        }
    }

    @Operation(summary = "Поиск модели пылесоса по имени")
    @GetMapping("/search-by-name")
    public ResponseEntity searchByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(smartphoneModelService.getByName(name));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск модели пылесоса по цвету")
    @GetMapping("/search-by-color")
    public ResponseEntity searchByColor(@RequestParam String color) {
        try {
            return ResponseEntity.ok(smartphoneModelService.getByColor(color));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск модели пылесоса по цене")
    @GetMapping("/search-by-price")
    public ResponseEntity searchByPrice(@RequestParam Double low, Double high) {
        try {
            return ResponseEntity.ok(smartphoneModelService.getByPrice(low, high));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск модели пылесоса по любым параметрам (можно с пустыми)")
    @GetMapping
    public ResponseEntity getAllWithFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long serialNumber,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) Double lowPrice,
            @RequestParam(required = false) Double highPrice,
            @RequestParam(required = false) Integer volumeOfMemory,
            @RequestParam(required = false) Integer numberOfCameras,
            @RequestParam(required = false) Boolean inStock
    ) {
        try {
            return ResponseEntity.ok(smartphoneModelService.getByParams
                    (name, serialNumber, color, size, lowPrice, highPrice, volumeOfMemory, numberOfCameras, inStock));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
