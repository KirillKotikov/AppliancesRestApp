package ru.kotikov.appliances.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.dto.TelevisionModelDto;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.services.ApplianceModelService;

@RestController
@RequestMapping("/television-models")
public class TelevisionModelController {

    final ApplianceModelService televisionModelService;

    public TelevisionModelController(ApplianceModelService televisionModelService) {
        this.televisionModelService = televisionModelService;
    }

    @Operation(summary = "Создать новую модель телевизора")
    @PostMapping
    public ResponseEntity create(@RequestBody TelevisionModelDto televisionModel,
                                 @RequestParam Long televisionId) {
        try {
            return ResponseEntity.ok(televisionModelService.create(televisionModel, televisionId));
        } catch (ModelAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Получить список всех моделей телевизоров")
    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(televisionModelService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск модели телевизора по id")
    @GetMapping("/{id}")
    public ResponseEntity searchById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(televisionModelService.getById(id));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Обновление модели телевизора")
    @PutMapping
    public ResponseEntity update(@RequestBody TelevisionModelDto televisionModel) {
        try {
            televisionModelService.update(televisionModel);
            return ResponseEntity.ok().body("Модель телевизора успешно обновлена!");
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Удаление модели телевизора по id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            televisionModelService.delete(id);
        } catch (ModelNotFoundException e) {
            e.getMessage();
        }
    }

    @Operation(summary = "Поиск модели телевизора по имени (работает и по частям слова)")
    @GetMapping("/search-by-name")
    public ResponseEntity searchByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(televisionModelService.getByName(name));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск модели телевизора по цвету (работает и по частям слова)")
    @GetMapping("/search-by-color")
    public ResponseEntity searchByColor(@RequestParam String color) {
        try {
            return ResponseEntity.ok(televisionModelService.getByColor(color));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск модели телевизора по цене (вводится диапазон от и до)")
    @GetMapping("/search-by-price")
    public ResponseEntity searchByPrice(@RequestParam Double low, Double high) {
        try {
            return ResponseEntity.ok(televisionModelService.getByPrice(low, high));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск модели телевизора по любым параметрам (можно с пустыми)")
    @GetMapping
    public ResponseEntity getAllWithFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long serialNumber,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) Double lowPrice,
            @RequestParam(required = false) Double highPrice,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String technology,
            @RequestParam(required = false) Boolean inStock
    ) {
        try {
            return ResponseEntity.ok(televisionModelService.getByParams
                    (name, serialNumber, color, size, lowPrice, highPrice, category, technology, inStock));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}