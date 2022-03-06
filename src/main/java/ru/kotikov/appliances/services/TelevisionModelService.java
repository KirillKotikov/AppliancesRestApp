package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.TelevisionEntity;
import ru.kotikov.appliances.entities.TelevisionModelEntity;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.models.Television;
import ru.kotikov.appliances.models.TelevisionModel;
import ru.kotikov.appliances.repository.TelevisionModelRepo;
import ru.kotikov.appliances.repository.TelevisionRepo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TelevisionModelService {

    @Autowired
    private TelevisionModelRepo televisionModelRepo;
    @Autowired
    private TelevisionRepo televisionRepo;

    public TelevisionModel create(TelevisionModelEntity televisionModel, Long televisionId) throws ModelAlreadyExistException {
        if (televisionModelRepo.findByName(televisionModel.getName()) != null) {
            throw new ModelAlreadyExistException("Модель телевизора с таким именем уже существует!");
        }
        TelevisionEntity television = televisionRepo.findById(televisionId).get();
        televisionModel.setTelevision(television);
        return TelevisionModel.toModel(televisionModelRepo.save(televisionModel));
    }

    public List<TelevisionModel> getAll() {
        return televisionModelRepo.findAll().stream()
                .sorted((Comparator.comparing(TelevisionModelEntity::getPrice)))
                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(TelevisionModel::toModel).collect(Collectors.toList());
    }

    public TelevisionModel getOne(Long id) throws ModelNotFoundException {
        TelevisionModelEntity television = televisionModelRepo.findById(id).get();
        if (television == null) {
            throw new ModelNotFoundException("Модель телевизора не найдена!");
        }
        return TelevisionModel.toModel(television);
    }

    public TelevisionModelEntity update(TelevisionModelEntity televisionModel) throws ModelNotFoundException {
        TelevisionModelEntity television = televisionModelRepo.findById(televisionModel.getId()).get();
        if (television == null) {
            throw new ModelNotFoundException("Модель телевизора не найдена!");
        }
        return televisionModelRepo.saveAndFlush(televisionModel);
    }

    public Long delete(Long id) throws ModelNotFoundException {
        TelevisionModelEntity television = televisionModelRepo.findById(id).get();
        if (television == null) {
            throw new ModelNotFoundException("Модель телевизора не найдена!");
        }
        televisionModelRepo.deleteById(id);
        return id;
    }

    public List<TelevisionModel> searchForName(String name) throws ModelNotFoundException {
        List<TelevisionModel> televisionModels = televisionModelRepo.findAll().stream().filter(x -> x.getName().equalsIgnoreCase(name))
                .map(TelevisionModel::toModel).sorted().collect(Collectors.toList());
        if (televisionModels == null) throw new ModelNotFoundException("Модель с таким именем не найдена!");
        return televisionModels;
    }
}
