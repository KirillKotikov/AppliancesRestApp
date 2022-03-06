package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.FridgeEntity;
import ru.kotikov.appliances.entities.FridgeModelEntity;
import ru.kotikov.appliances.entities.HooverModelEntity;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.models.FridgeModel;
import ru.kotikov.appliances.models.HooverModel;
import ru.kotikov.appliances.models.Television;
import ru.kotikov.appliances.repository.FridgeModelRepo;
import ru.kotikov.appliances.repository.FridgeRepo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FridgeModelService {
    @Autowired
    private FridgeModelRepo fridgeModelRepo;
    @Autowired
    private FridgeRepo fridgeRepo;

    public FridgeModel create(FridgeModelEntity fridgeModel, Long id) throws ModelAlreadyExistException {
        if (fridgeModelRepo.findByName(fridgeModel.getName()) != null) {
            throw new ModelAlreadyExistException("Модель холодильника с таким именем уже существует!");
        }
        FridgeEntity fridge = fridgeRepo.findById(id).get();
        fridgeModel.setFridge(fridge);
        return FridgeModel.toModel(fridgeModelRepo.save(fridgeModel));
    }

    public List<FridgeModel> getAll() {
        return fridgeModelRepo.findAll().stream()
                .sorted((Comparator.comparing(FridgeModelEntity::getPrice)))
                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(FridgeModel::toModel).collect(Collectors.toList());
    }

    public FridgeModel getOne(Long id) throws ModelNotFoundException {
        FridgeModelEntity fridgeModel = fridgeModelRepo.findById(id).get();
        if (fridgeModel == null) {
            throw new ModelNotFoundException("Модель холодильника не найдена!");
        }
        return FridgeModel.toModel(fridgeModel);
    }

    public FridgeModelEntity update(FridgeModelEntity fridgeModel) throws ModelNotFoundException {
        FridgeModelEntity fridgeModelEntity = fridgeModelRepo.findById(fridgeModel.getId()).get();
        if (fridgeModelEntity == null) {
            throw new ModelNotFoundException("Модель холодильника не найдена!");
        }
        return fridgeModelRepo.saveAndFlush(fridgeModel);
    }

    public Long delete(Long id) throws ModelNotFoundException {
        FridgeModelEntity fridge = fridgeModelRepo.findById(id).get();
        if (fridge == null) {
            throw new ModelNotFoundException("Модель холодильника не найдена!");
        }
        fridgeModelRepo.deleteById(id);
        return id;
    }

    public List<FridgeModel> searchForName(String name) {
        return fridgeModelRepo.findAll().stream().filter(x -> x.getName().equalsIgnoreCase(name))
                .map(FridgeModel::toModel).sorted().collect(Collectors.toList());
    }
}
