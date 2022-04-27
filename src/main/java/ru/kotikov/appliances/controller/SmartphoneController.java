package ru.kotikov.appliances.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.dto.ApplianceDto;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.services.ApplianceService;

@RestController
@RequestMapping("/smartphones")
public class SmartphoneController {

    private final ApplianceService smartphoneService;

    public SmartphoneController(ApplianceService smartphoneService) {
        this.smartphoneService = smartphoneService;
    }

    @Operation(summary = "Создать новую группу смартфонов")
    @PostMapping
    public ResponseEntity create(@RequestBody ApplianceDto smartphone) {
        try {
            return ResponseEntity.ok(smartphoneService.create(smartphone));
        } catch (ApplianceAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Список моделей пуст!");
        }
    }

    @Operation(summary = "Получение списка всех групп смартфонов")
    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(smartphoneService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Поиск группы смартфонов по id")
    @GetMapping
    public ResponseEntity searchById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(smartphoneService.getById(id));
        } catch (ApplianceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Обновление группы смартфонов")
    @PutMapping
    public ResponseEntity update(@RequestBody ApplianceDto smartphone) {
        try {
            smartphoneService.update(smartphone);
            return ResponseEntity.ok().body("Прибор успешно обновлен!");
        } catch (ApplianceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @Operation(summary = "Удаление группы смартфонов по id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            smartphoneService.delete(id);
        } catch (ApplianceNotFoundException e) {
            e.getMessage();
        }
    }

    @Operation(summary = "Поиск группы смартфонов по имени")
    @GetMapping("/search-by-name")
    public ResponseEntity searchByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(smartphoneService.findByName(name));
        } catch (ApplianceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
