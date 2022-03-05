package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.ApplianceEntity;
import ru.kotikov.appliances.entities.TelevisionEntity;
import ru.kotikov.appliances.exptions.TelevisionAlreadyExistException;
import ru.kotikov.appliances.exptions.TelevisionNotFoundException;
import ru.kotikov.appliances.models.Appliance;
import ru.kotikov.appliances.models.Television;
import ru.kotikov.appliances.repository.ApplianceRepo;
import ru.kotikov.appliances.repository.TelevisionRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TelevisionService {

    @Autowired
    private ApplianceRepo applianceRepo;
    @Autowired
    private TelevisionRepo televisionRepo;

    public Television create(TelevisionEntity television, Long applianceId) throws TelevisionAlreadyExistException {
        if (televisionRepo.findByTelevisionName(television.getTelevisionName()) != null) {
            throw new TelevisionAlreadyExistException("Телевизор с таким именем уже существует!");
        }
        ApplianceEntity appliance = applianceRepo.findById(applianceId).get();
        television.setAppliance(appliance);
        return Television.toModel(televisionRepo.save(television));
    }

    public List<Television> getAll() {
        return televisionRepo.findAll().stream().sorted((o1, o2) -> o1.getTelevisionName().compareToIgnoreCase(o2.getTelevisionName()))
                .map(Television::toModel).collect(Collectors.toList());
    }

    public Television getOne(Long id) throws TelevisionNotFoundException {
        TelevisionEntity television = televisionRepo.findById(id).get();
        if (television == null) {
            throw new TelevisionNotFoundException("Телевизор не найден!");
        }
        return Television.toModel(television);
    }

    public TelevisionEntity update(TelevisionEntity television) {
        return televisionRepo.saveAndFlush(television);
    }

    public Long delete(Long id) {
        televisionRepo.deleteById(id);
        return id;
    }
}
