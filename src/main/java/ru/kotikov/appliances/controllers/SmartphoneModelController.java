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

    @GetMapping
    public ResponseEntity getOne(@RequestParam Long id) {
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

    @GetMapping("/search-for-name")
    public ResponseEntity searchForName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(smartphoneModelService.searchForName(name));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
