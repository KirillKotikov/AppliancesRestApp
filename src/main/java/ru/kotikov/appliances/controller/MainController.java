package ru.kotikov.appliances.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotikov.appliances.services.MainService;

@RestController
@RequestMapping("/")
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @Operation(summary = "Получить список всех групп техники с моделями в наличии")
    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(mainService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}