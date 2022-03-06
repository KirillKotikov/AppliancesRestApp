package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.TelevisionEntity;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.models.Television;
import ru.kotikov.appliances.repository.TelevisionRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TelevisionService {

    @Autowired
    private TelevisionRepo televisionRepo;

    public Television create(TelevisionEntity television) throws ApplianceAlreadyExistException {
        if (televisionRepo.findByName(television.getName()) != null) {
            throw new ApplianceAlreadyExistException("Телевизор с таким именем уже существует!");
        }
        return Television.toModel(televisionRepo.save(television));
    }

    public List<Television> getAll() {
        return televisionRepo.findAll().stream()
                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(Television::toModel).collect(Collectors.toList());
    }

    public Television getOne(Long id) throws ApplianceNotFoundException {
        TelevisionEntity television = televisionRepo.findById(id).get();
        if (television == null) {
            throw new ApplianceNotFoundException("Телевизор не найден!");
        }
        return Television.toModel(television);
    }

    public TelevisionEntity update(TelevisionEntity television) throws ApplianceNotFoundException {
        TelevisionEntity televisionEntity = televisionRepo.findById(television.getId()).get();
        if (televisionEntity == null) {
            throw new ApplianceNotFoundException("Телевизор не найден!");
        }
        return televisionRepo.saveAndFlush(television);
    }

    public Long delete(Long id) throws ApplianceNotFoundException {
        TelevisionEntity television = televisionRepo.findById(id).get();
        if (television == null) {
            throw new ApplianceNotFoundException("Телевизор не найден!");
        }
        televisionRepo.deleteById(id);
        return id;
    }

    public List<Television> searchByName(String name) throws ApplianceNotFoundException {
        List<Television> televisions = televisionRepo.findAll().stream()
                .filter(x -> x.getName().equalsIgnoreCase(name))
                .map(Television::toModel).sorted().collect(Collectors.toList());
        if (televisions.size() == 0) throw new ApplianceNotFoundException("Техника с таким именем не найдена!");
        return televisions;
    }
}
