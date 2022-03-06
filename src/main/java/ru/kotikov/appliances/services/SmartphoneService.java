package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.SmartphoneEntity;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.models.Smartphone;
import ru.kotikov.appliances.models.Television;
import ru.kotikov.appliances.repository.SmartphoneRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmartphoneService {

    @Autowired
    private SmartphoneRepo smartphoneRepo;

    public Smartphone create(SmartphoneEntity smartphone) throws ApplianceAlreadyExistException {
        if (smartphoneRepo.findByName(smartphone.getName()) != null) {
            throw new ApplianceAlreadyExistException("Смартфон с таким именем уже существует!");
        }
        return Smartphone.toModel(smartphoneRepo.save(smartphone));
    }

    public List<Smartphone> getAll() {
        return smartphoneRepo.findAll().stream().sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(Smartphone::toModel).collect(Collectors.toList());
    }

    public Smartphone getOne(Long id) throws ApplianceNotFoundException {
        SmartphoneEntity smartphoneEntity = smartphoneRepo.findById(id).get();
        if (smartphoneEntity == null) {
            throw new ApplianceNotFoundException("Смартфон не найден!");
        }
        return Smartphone.toModel(smartphoneEntity);
    }

    public SmartphoneEntity update(SmartphoneEntity smartphoneEntity) throws ApplianceNotFoundException {
        SmartphoneEntity smartphone = smartphoneRepo.findById(smartphoneEntity.getId()).get();
        if (smartphone == null) {
            throw new ApplianceNotFoundException("Смартфон не найден!");
        }
        return smartphoneRepo.saveAndFlush(smartphoneEntity);
    }

    public String delete(Long id) throws ApplianceNotFoundException {
        SmartphoneEntity smartphoneEntity = smartphoneRepo.findById(id).get();
        if (smartphoneEntity == null) {
            throw new ApplianceNotFoundException("Смартфон не найден!");
        }
        smartphoneRepo.deleteById(id);
        return "Смартфон с id = " + id + " успешно удалён!";
    }

    public List<Smartphone> searchForName(String name) throws ApplianceNotFoundException {
        List<Smartphone> smartphones = smartphoneRepo.findAll().stream()
                .filter(x -> x.getName().equalsIgnoreCase(name))
                .map(Smartphone::toModel).sorted().collect(Collectors.toList());
        if (smartphones == null) throw new ApplianceNotFoundException("Техника с таким именем не найдена!");
        return smartphones;
    }
}
