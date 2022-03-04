package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.TelevisionEntity;
import ru.kotikov.appliances.exptions.TelevisionAlreadyExistException;
import ru.kotikov.appliances.exptions.TelevisionNotFoundException;
import ru.kotikov.appliances.models.Television;
import ru.kotikov.appliances.repository.TelevisionRepo;

@Service
public class TelevisionService {

    @Autowired
    private TelevisionRepo televisionRepo;

    public TelevisionEntity registration(TelevisionEntity television) throws TelevisionAlreadyExistException {
        if (televisionRepo.findByTelevisionName(television.getTelevisionName()) != null) {
            throw new TelevisionAlreadyExistException("Пользователь с таким именем уже существует!");
        }
       return televisionRepo.save(television);
    }

    public Television getOne (Long id) throws TelevisionNotFoundException {
        TelevisionEntity television = televisionRepo.findById(id).get();
        if (television == null) {
            throw new TelevisionNotFoundException("Телевизор не найден!");
        }
        return Television.toModel(television);
    }

    public Long delete (Long id) {
        televisionRepo.deleteById(id);
        return id;
    }
}
