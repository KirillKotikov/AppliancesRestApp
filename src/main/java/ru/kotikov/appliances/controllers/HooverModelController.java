package ru.kotikov.appliances.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.entities.HooverModelEntity;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.services.HooverModelService;

@RestController
@RequestMapping("/hoover-models")
public class HooverModelController {

        @Autowired
        HooverModelService hooverModelService;

        @PostMapping
        public ResponseEntity create(@RequestBody HooverModelEntity hooverModel,
                                     @RequestParam Long hooverId) {
            try {
                return ResponseEntity.ok(hooverModelService.create(hooverModel, hooverId));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        }

        @GetMapping("/get-all")
        public ResponseEntity getAll() {
            try {
                return ResponseEntity.ok(hooverModelService.getAll());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        }

        @GetMapping
        public ResponseEntity getOne(@RequestParam Long id) {
            try {
                return ResponseEntity.ok(hooverModelService.getOne(id));
            } catch (ModelNotFoundException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        }

        @PutMapping
        public ResponseEntity update(@RequestBody HooverModelEntity hooverModel) {
            try {
                hooverModelService.update(hooverModel);
                return ResponseEntity.ok().body("Модель пылесоса успешно обновлена!");
            } catch (ModelNotFoundException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity delete(@PathVariable Long id) {
            try {
                return ResponseEntity.ok(hooverModelService.delete(id));
            } catch (ModelNotFoundException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        }

    @GetMapping("/search-by-name")
    public ResponseEntity searchByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(hooverModelService.searchByName(name));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/search-by-color")
    public ResponseEntity searchByColor(@RequestParam String color) {
        try {
            return ResponseEntity.ok(hooverModelService.searchByColor(color));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/search-by-price")
    public ResponseEntity searchByPrice(@RequestParam Double low, Double high) {
        try {
            return ResponseEntity.ok(hooverModelService.searchByPrice(low, high));
        } catch (ModelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
