package ru.kotikov.appliances.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotikov.appliances.services.MainService;

@RestController
@RequestMapping("/get-all-appliances")
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(mainService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
