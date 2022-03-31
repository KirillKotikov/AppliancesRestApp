package ru.kotikov.appliances.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.entities.SmartphoneModelEntity;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.services.SmartphoneModelService;

@RestController
@RequestMapping("/smartphone-models")
public class SmartphoneModelController {

    @Autowired
    SmartphoneModelService smartphoneModelService;

    @PostMapping
    public ResponseEntity create(@RequestBody SmartphoneModelEntity smartphoneModel,
                                 @RequestParam Long smartphoneId) {
        try {
            return ResponseEntity.ok(smartphoneModelService.create(smartphoneModel, smartphoneId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(smartphoneModelService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity searchById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(smartphoneModelService.getOne(id));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody SmartphoneModelEntity smartphoneModel) {
        try {
            smartphoneModelService.update(smartphoneModel);
            return ResponseEntity.ok().body("Модель смартфона успешно обновлена!");
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(smartphoneModelService.delete(id));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/search-by-name")
    public ResponseEntity searchByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(smartphoneModelService.searchByName(name));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/search-by-color")
    public ResponseEntity searchByColor(@RequestParam String color) {
        try {
            return ResponseEntity.ok(smartphoneModelService.searchByColor(color));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/search-by-price")
    public ResponseEntity searchByPrice(@RequestParam Double low, Double high) {
        try {
            return ResponseEntity.ok(smartphoneModelService.searchByPrice(low, high));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity getAllWithFilters(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "0") Long serialNumber,
            @RequestParam(required = false, defaultValue = "") String color,
            @RequestParam(required = false, defaultValue = "") String size,
            @RequestParam(required = false, defaultValue = "0") Double lowPrice,
            @RequestParam(required = false, defaultValue = "20000000") Double highPrice,
            @RequestParam(required = false, defaultValue = "0") Integer volumeOfMemory,
            @RequestParam(required = false, defaultValue = "0") Integer numberOfCameras,
            @RequestParam(required = false, defaultValue = "true") Boolean inStock
    ) {
        try {
            return ResponseEntity.ok(smartphoneModelService.searchWithFilters
                    (name, serialNumber, color, size, lowPrice, highPrice, volumeOfMemory, numberOfCameras, inStock));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
