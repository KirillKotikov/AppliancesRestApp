package ru.kotikov.appliances.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.entities.HooverEntity;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
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
            } catch (ApplianceAlreadyExistException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (NullPointerException e) {
                return ResponseEntity.badRequest().body("Список моделей пуст!");
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

    @GetMapping("/search-for-name")
    public ResponseEntity searchForName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(hooverService.searchForName(name));
        } catch (ApplianceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
