package ru.kotikov.appliances.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.dto.ApplianceDto;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.services.ApplianceService;

@RestController
@RequestMapping("/fridges")
public class FridgeController {

    private final ApplianceService fridgeService;

    public FridgeController(ApplianceService fridgeService) {
        this.fridgeService = fridgeService;
    }

    @Operation(summary = "Создать новую группу холодильников")
    @PostMapping
    public ResponseEntity create(@RequestBody ApplianceDto fridge) {
        try {
            return ResponseEntity.ok(fridgeService.create(fridge));
        } catch (ApplianceAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Список моделей пуст!");
        }
    }

    @Operation(summary = "Получение списка всех групп холодильников")
    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(fridgeService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск группы холодильников по id")
    @GetMapping
    public ResponseEntity searchById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(fridgeService.getById(id));
        } catch (ApplianceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Обновление группы компьютеров")
    @PutMapping
    public ResponseEntity update(@RequestBody ApplianceDto fridge) {
        try {
            fridgeService.update(fridge);
            return ResponseEntity.ok().body("Прибор успешно обновлен!");
        } catch (ApplianceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Удаление группы компьютеров по id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            fridgeService.delete(id);
        } catch (ApplianceNotFoundException e) {
            e.getMessage();
        }
    }

    @Operation(summary = "Поиск группы компьютеров по имени")
    @GetMapping("/search-by-name")
    public ResponseEntity searchByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(fridgeService.findByName(name));
        } catch (ApplianceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
