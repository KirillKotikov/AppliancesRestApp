package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.FridgeModelEntity;
import ru.kotikov.appliances.entities.SmartphoneEntity;
import ru.kotikov.appliances.entities.SmartphoneModelEntity;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.models.SmartphoneModel;
import ru.kotikov.appliances.repository.SmartphoneModelRepo;
import ru.kotikov.appliances.repository.SmartphoneRepo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmartphoneModelService {
    @Autowired
    private SmartphoneModelRepo smartphoneModelRepo;
    @Autowired
    private SmartphoneRepo smartphoneRepo;

    public SmartphoneModel create(SmartphoneModelEntity smartphoneModel, Long id) throws ModelAlreadyExistException {
        if (smartphoneModelRepo.findByName(smartphoneModel.getName()) != null) {
            throw new ModelAlreadyExistException("Модель смартфона с таким именем уже существует!");
        }
        SmartphoneEntity smartphone = smartphoneRepo.findById(id).get();
        smartphoneModel.setSmartphone(smartphone);
        return SmartphoneModel.toModel(smartphoneModelRepo.save(smartphoneModel));
    }

    public List<SmartphoneModel> getAll() {
        return smartphoneModelRepo.findAll().stream().sorted((Comparator.comparing(SmartphoneModelEntity::getPrice)))
                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(SmartphoneModel::toModel).collect(Collectors.toList());
    }

    public SmartphoneModel getOne(Long id) throws ModelNotFoundException {
        SmartphoneModelEntity smartphoneModel = smartphoneModelRepo.findById(id).get();
        if (smartphoneModel == null) {
            throw new ModelNotFoundException("Модель смартфона не найдена!");
        }
        return SmartphoneModel.toModel(smartphoneModel);
    }

    public SmartphoneModelEntity update(SmartphoneModelEntity smartphoneModel) throws ModelNotFoundException {
        SmartphoneModelEntity smartphoneModelEntity = smartphoneModelRepo.findById(smartphoneModel.getId()).get();
        if (smartphoneModelEntity == null) {
            throw new ModelNotFoundException("Модель смартфона не найдена!");
        }
        return smartphoneModelRepo.saveAndFlush(smartphoneModel);
    }

    public Long delete(Long id) throws ModelNotFoundException {
        SmartphoneModelEntity smartphoneModelEntity = smartphoneModelRepo.findById(id).get();
        if (smartphoneModelEntity == null) {
            throw new ModelNotFoundException("Модель смартфона не найдена!");
        }
        smartphoneModelRepo.deleteById(id);
        return id;
    }
}
