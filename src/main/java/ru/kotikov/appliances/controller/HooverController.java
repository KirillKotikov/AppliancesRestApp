package ru.kotikov.appliances.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.dto.ApplianceDto;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.services.ApplianceService;

@RestController
@RequestMapping("/hoovers")
public class HooverController {

    private final ApplianceService hooverService;

    public HooverController(ApplianceService hooverService) {
        this.hooverService = hooverService;
    }

    @Operation(summary = "Создать новую группу пылесосов")
    @PostMapping
    public ResponseEntity create(@RequestBody ApplianceDto hoover) {
        try {
            return ResponseEntity.ok(hooverService.create(hoover));
        } catch (ApplianceAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Список моделей пуст!");
        }
    }

    @Operation(summary = "Получение списка всех групп пылесосов")
    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(hooverService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск группы пылесосов по id")
    @GetMapping
    public ResponseEntity searchById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(hooverService.getById(id));
        } catch (ApplianceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Обновление группы пылесосов")
    @PutMapping
    public ResponseEntity update(@RequestBody ApplianceDto hoover) {
        try {
            hooverService.update(hoover);
            return ResponseEntity.ok().body("Прибор успешно обновлен!");
        } catch (ApplianceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Удаление группы пылесосов по id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            hooverService.delete(id);
        } catch (ApplianceNotFoundException e) {
            e.getMessage();
        }
    }

    @Operation(summary = "Поиск группы пылесосов по имени (работает и по частям слова)")
    @GetMapping("/search-by-name")
    public ResponseEntity searchByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(hooverService.findByName(name));
        } catch (ApplianceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}