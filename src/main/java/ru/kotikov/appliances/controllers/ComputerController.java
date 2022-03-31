package ru.kotikov.appliances.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.entities.ComputerEntity;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.services.ComputerService;

@RestController
@RequestMapping("/computers")
public class ComputerController {

    @Autowired
    private ComputerService computerService;

    @PostMapping // добавляет новую модель в базу данных
    public ResponseEntity create(@RequestBody ComputerEntity computerEntity) {
        try { // если все правильно, сохраняет модель в базе данных и выводит ее на странице
            return ResponseEntity.ok(computerService.create(computerEntity));
        } catch (ApplianceAlreadyExistException e) { // если такая модель уже существует, выдает индивидуальную ошибку
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NullPointerException e) { // если список моделей пуст, то выводит информацию об этом
            return ResponseEntity.badRequest().body("Список моделей пуст!");
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity getAll() { // данный запрос выводит все модели в наличии
        try {
            return ResponseEntity.ok(computerService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity getOne(@RequestParam Long id) { // выводит группу вида моделей с определенным ID
        try {
            return ResponseEntity.ok(computerService.getOne(id));
        } catch (ApplianceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping // обновляет параметры группы вида модели
    public ResponseEntity update(@RequestBody ComputerEntity computerEntity) {
        try {
            computerService.update(computerEntity);
            return ResponseEntity.ok().body("Прибор успешно обновлен!");
        } catch (ApplianceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}") // удаляет группу вида моделей по ID
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(computerService.delete(id));
        } catch (ApplianceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/search-by-name") //поиск через параметры по имени
    public ResponseEntity searchByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(computerService.searchByName(name));
        } catch (ApplianceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
