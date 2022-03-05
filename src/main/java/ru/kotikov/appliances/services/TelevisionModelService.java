package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.TelevisionEntity;
import ru.kotikov.appliances.entities.TelevisionModelEntity;
import ru.kotikov.appliances.exptions.ApplianceNotFoundException;
import ru.kotikov.appliances.exptions.TelevisionAlreadyExistException;
import ru.kotikov.appliances.exptions.TelevisionModelAlreadyExistException;
import ru.kotikov.appliances.exptions.TelevisionModelNotFoundException;
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

    public TelevisionModel create(TelevisionModelEntity televisionModel, Long televisionId) throws TelevisionModelAlreadyExistException {
        if (televisionModelRepo.findByTelevisionModelName(televisionModel.getTelevisionModelName()) != null) {
            throw new TelevisionModelAlreadyExistException("Модель телевизора с таким именем уже существует!");
        }
        TelevisionEntity television = televisionRepo.findById(televisionId).get();
        televisionModel.setTelevision(television);
        return TelevisionModel.toTelevisionModel(televisionModelRepo.save(televisionModel));
    }

    public List<TelevisionModel> getAll() {
        return televisionModelRepo.findAll().stream().sorted((Comparator.comparing(TelevisionModelEntity::getPrice)))
                .sorted((o1, o2) -> o1.getTelevisionModelName().compareToIgnoreCase(o2.getTelevisionModelName()))
                .map(TelevisionModel::toTelevisionModel).collect(Collectors.toList());
    }

    public TelevisionModel getOne(Long id) throws TelevisionModelNotFoundException {
        TelevisionModelEntity television = televisionModelRepo.findById(id).get();
        if (television == null) {
            throw new TelevisionModelNotFoundException("Модель телевизора не найдена!");
        }
        return TelevisionModel.toTelevisionModel(television);
    }

    public TelevisionModelEntity update(TelevisionModelEntity televisionModel) {
        return televisionModelRepo.saveAndFlush(televisionModel);
    }

    public Long delete(Long id) {
        televisionModelRepo.deleteById(id);
        return id;
    }

    public TelevisionModel inStockTelevisionModel(Long id) {
        TelevisionModelEntity televisionModel = televisionModelRepo.findById(id).get();
        televisionModel.setInStock(!televisionModel.getInStock());
        return TelevisionModel.toTelevisionModel(televisionModelRepo.save(televisionModel));
    }
}
