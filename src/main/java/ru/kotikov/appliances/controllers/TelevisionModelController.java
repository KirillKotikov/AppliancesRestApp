package ru.kotikov.appliances.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.entities.TelevisionEntity;
import ru.kotikov.appliances.entities.TelevisionModelEntity;
import ru.kotikov.appliances.exptions.TelevisionModelNotFoundException;
import ru.kotikov.appliances.services.TelevisionModelService;

@RestController
@RequestMapping("/television-models")
public class TelevisionModelController {

    @Autowired
    TelevisionModelService televisionModelService;

    @PostMapping
    public ResponseEntity create(@RequestBody TelevisionModelEntity televisionModel,
                                 @RequestParam Long televisionId) {
        try {
            return ResponseEntity.ok(televisionModelService.create(televisionModel, televisionId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(televisionModelService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity getOne(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(televisionModelService.getOne(id));
        } catch (TelevisionModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody TelevisionModelEntity televisionModel) {
        try {
            televisionModelService.update(televisionModel);
            return ResponseEntity.ok().body("Модель телевизора успешно обновлена!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(televisionModelService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity inStockTelevisionModel(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(televisionModelService.inStockTelevisionModel(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
