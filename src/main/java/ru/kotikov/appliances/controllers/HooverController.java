package ru.kotikov.appliances.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.entities.HooverEntity;
import ru.kotikov.appliances.entities.TelevisionEntity;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.services.HooverService;

@RestController
@RequestMapping("/hoovers")
public class HooverController {

        @Autowired
        private HooverService hooverService;

        @PostMapping
        public ResponseEntity create(@RequestBody HooverEntity hoover) {
            try {
                return ResponseEntity.ok(hooverService.create(hoover));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        }

        @GetMapping("/get-all")
        public ResponseEntity getAll() {
            try {
                return ResponseEntity.ok(hooverService.getAll());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        }

        @GetMapping
        public ResponseEntity getOne(@RequestParam Long id) {
            try {
                return ResponseEntity.ok(hooverService.getOne(id));
            } catch (ApplianceNotFoundException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        }

        @PutMapping
        public ResponseEntity update(@RequestBody HooverEntity hoover) {
            try {
                hooverService.update(hoover);
                return ResponseEntity.ok().body("Прибор успешно обновлен!");
            } catch (ApplianceNotFoundException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity delete(@PathVariable Long id) {
            try {
                return ResponseEntity.ok(hooverService.delete(id));
            } catch (ApplianceNotFoundException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            }
        }
}
