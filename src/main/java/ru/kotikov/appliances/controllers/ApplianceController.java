package ru.kotikov.appliances.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.entities.ApplianceEntity;
import ru.kotikov.appliances.exptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exptions.ApplianceNotFoundException;
import ru.kotikov.appliances.exptions.TelevisionNotFoundException;
import ru.kotikov.appliances.services.ApplianceService;

@RestController
@RequestMapping("/appliances")
public class ApplianceController {

    @Autowired
    private ApplianceService applianceService;

    @PostMapping
    public ResponseEntity createAppliance(@RequestBody ApplianceEntity appliance) {
        try {
            applianceService.createAppliance(appliance);
            return ResponseEntity.ok().body("Прибор успешно сохранен!");
        } catch (ApplianceAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

        @GetMapping
    public ResponseEntity getOneAppliance(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(applianceService.getOne(id));
        } catch (ApplianceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAppliance(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(applianceService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllAppliances() {
        try {
            return ResponseEntity.ok("Сервер работает");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }


}


