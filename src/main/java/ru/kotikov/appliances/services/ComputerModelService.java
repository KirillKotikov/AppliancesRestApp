package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.ComputerEntity;
import ru.kotikov.appliances.entities.ComputerModelEntity;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.models.ComputerModel;
import ru.kotikov.appliances.models.Television;
import ru.kotikov.appliances.repository.ComputerModelRepo;
import ru.kotikov.appliances.repository.ComputerRepo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComputerModelService {

    @Autowired
    private ComputerModelRepo computerModelRepo;
    @Autowired
    private ComputerRepo computerRepo;

    public ComputerModel create(ComputerModelEntity computerModelEntity, Long computerId) throws ModelAlreadyExistException {
        if (computerModelRepo.findByName(computerModelEntity.getName()) != null) {
            throw new ModelAlreadyExistException("Модель компьютера с таким именем уже существует!");
        }
        ComputerEntity computerEntity = computerRepo.findById(computerId).get();
        computerModelEntity.setComputer(computerEntity);
        return ComputerModel.toModel(computerModelRepo.save(computerModelEntity));
    }

    public List<ComputerModel> getAll() {
        return computerModelRepo.findAll().stream()
                .sorted((Comparator.comparing(ComputerModelEntity::getPrice)))
                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(ComputerModel::toModel).collect(Collectors.toList());
    }

    public ComputerModel getOne(Long id) throws ModelNotFoundException {
        ComputerModelEntity computerModelEntity = computerModelRepo.findById(id).get();
        if (computerModelEntity == null) {
            throw new ModelNotFoundException("Модель компьютера не найдена!");
        }
        return ComputerModel.toModel(computerModelEntity);
    }

    public ComputerModelEntity update(ComputerModelEntity computerModelEntity) throws ModelNotFoundException {
        ComputerModelEntity computerModel = computerModelRepo.findById(computerModelEntity.getId()).get();
        if (computerModel == null) {
            throw new ModelNotFoundException("Модель компьютера не найдена!");
        }
        return computerModelRepo.saveAndFlush(computerModelEntity);
    }

    public Long delete(Long id) throws ModelNotFoundException {
        ComputerModelEntity computerModelEntity = computerModelRepo.findById(id).get();
        if (computerModelEntity == null) {
            throw new ModelNotFoundException("Модель компьютера не найдена!");
        }
        computerModelRepo.deleteById(id);
        return id;
    }

    public List<ComputerModel> searchForName(String name) {
        return computerModelRepo.findAll().stream().filter(x -> x.getName().equalsIgnoreCase(name))
                .map(ComputerModel::toModel).sorted().collect(Collectors.toList());
    }
}
