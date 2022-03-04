package ru.kotikov.appliances.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.entities.TelevisionModelEntity;
import ru.kotikov.appliances.services.TelevisionModelsService;

@RestController
@RequestMapping("/television-models")
public class TelevisionModelController {

    @Autowired
    TelevisionModelsService televisionModelsService;

    @PostMapping
    public ResponseEntity createTelevisionModel(@RequestBody TelevisionModelEntity televisionModel,
                                                @RequestParam Long televisionId) {
        try {
            return ResponseEntity.ok(televisionModelsService.createTelevisionModel(televisionModel, televisionId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping
    public ResponseEntity inStockTelevisionModel(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(televisionModelsService.inStockTelevisionModel(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
