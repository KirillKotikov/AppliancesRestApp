package ru.kotikov.appliances.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotikov.appliances.entities.TelevisionEntity;
import ru.kotikov.appliances.exptions.TelevisionAlreadyExistException;
import ru.kotikov.appliances.exptions.TelevisionNotFoundException;
import ru.kotikov.appliances.services.TelevisionService;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    @Autowired
    private TelevisionService televisionService;

    @PostMapping
    public ResponseEntity registration (@RequestBody TelevisionEntity television) {
        try {
            televisionService.registration(television);
            return ResponseEntity.ok().body("Телевизор успешно сохранен!");
        }catch (TelevisionAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage() );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity getOneTelevision(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(televisionService.getOne(id));
        } catch (TelevisionNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTelevision (@PathVariable Long id) {
        try {
            return ResponseEntity.ok(televisionService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
