package ru.kotikov.appliances.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.entities.HooverModelEntity;
import ru.kotikov.appliances.entities.TelevisionModelEntity;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.services.HooverModelService;
import ru.kotikov.appliances.services.TelevisionModelService;

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

        @PutMapping("/{id}")
        public ResponseEntity inStockTelevisionModel(@PathVariable Long id) {
            try {
                return ResponseEntity.ok(hooverModelService.inStockTelevisionModel(id));
            } catch (ModelNotFoundException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        }
}
