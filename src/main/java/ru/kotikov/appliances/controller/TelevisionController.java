package ru.kotikov.appliances.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.dto.ApplianceDto;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.services.ApplianceService;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private final ApplianceService televisionService;

    public TelevisionController(ApplianceService televisionService) {
        this.televisionService = televisionService;
    }

    @Operation(summary = "Создать новую группу телевизоров")
    @PostMapping
    public ResponseEntity create(@RequestBody ApplianceDto television) {
        try {
            return ResponseEntity.ok(televisionService.create(television));
        } catch (ApplianceAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Список моделей пуст!");
        }
    }

    @Operation(summary = "Получение списка всех групп телевизоров")
    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(televisionService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск группы телевизоров по id")
    @GetMapping
    public ResponseEntity searchById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(televisionService.getById(id));
        } catch (ApplianceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Обновление группы телевизоров")
    @PutMapping
    public ResponseEntity update(@RequestBody ApplianceDto television) {
        try {
            televisionService.update(television);
            return ResponseEntity.ok().body("Прибор успешно обновлен!");
        } catch (ApplianceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Удаление группы телевизоров по id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            televisionService.delete(id);
        } catch (ApplianceNotFoundException e) {
            e.getMessage();
        }
    }

    @Operation(summary = "Поиск группы телевизоров по имени (работает и по частям слова)")
    @GetMapping("/search-by-name")
    public ResponseEntity searchByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(televisionService.findByName(name));
        } catch (ApplianceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}