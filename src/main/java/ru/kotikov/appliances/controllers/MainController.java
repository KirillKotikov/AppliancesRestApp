package ru.kotikov.appliances.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.models.AbstractAppliance;
import ru.kotikov.appliances.models.Television;
import ru.kotikov.appliances.services.MainService;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    private MainService mainService;

    // Отображает полный список всей техники
    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(mainService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
