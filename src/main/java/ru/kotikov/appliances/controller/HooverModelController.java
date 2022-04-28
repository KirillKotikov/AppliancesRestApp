package ru.kotikov.appliances.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.dto.HooverModelDto;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.services.ApplianceModelService;

@RestController
@RequestMapping("/hoover-models")
public class HooverModelController {

    final ApplianceModelService hooverModelService;

    public HooverModelController(ApplianceModelService hooverModelService) {
        this.hooverModelService = hooverModelService;
    }

    @Operation(summary = "Создать новую модель пылесоса")
    @PostMapping
    public ResponseEntity create(@RequestBody HooverModelDto hooverModel,
                                 @RequestParam Long hooverId) {
        try {
            return ResponseEntity.ok(hooverModelService.create(hooverModel, hooverId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Получить список всех моделей пылесосов")
    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(hooverModelService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск модели пылесоса по id")
    @GetMapping("/{id}")
    public ResponseEntity searchById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(hooverModelService.getById(id));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Обновление модели пылесоса")
    @PutMapping
    public ResponseEntity update(@RequestBody HooverModelDto hooverModel) {
        try {
            hooverModelService.update(hooverModel);
            return ResponseEntity.ok().body("Модель пылесоса успешно обновлена!");
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
            hooverModelService.delete(id);
        } catch (ModelNotFoundException e) {
            e.getMessage();
        }
    }

    @Operation(summary = "Поиск модели пылесоса по имени (работает и по частям слова)")
    @GetMapping("/search-by-name")
    public ResponseEntity searchByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(hooverModelService.getByName(name));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск модели пылесоса по цвету (работает и по частям слова)")
    @GetMapping("/search-by-color")
    public ResponseEntity searchByColor(@RequestParam String color) {
        try {
            return ResponseEntity.ok(hooverModelService.getByColor(color));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск модели пылесоса по цене (вводится диапазон от и до)")
    @GetMapping("/search-by-price")
    public ResponseEntity searchByPrice(@RequestParam Double low, Double high) {
        try {
            return ResponseEntity.ok(hooverModelService.getByPrice(low, high));
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
            @RequestParam(required = false) Integer dustContainerVolume,
            @RequestParam(required = false) Integer numberOfModes,
            @RequestParam(required = false) Boolean inStock
    ) {
        try {
            return ResponseEntity.ok(hooverModelService.getByParams
                    (name, serialNumber, color, size, lowPrice, highPrice, dustContainerVolume, numberOfModes, inStock));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}