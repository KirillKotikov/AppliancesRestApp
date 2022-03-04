package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.TelevisionEntity;
import ru.kotikov.appliances.entities.TelevisionModelEntity;
import ru.kotikov.appliances.models.TelevisionModel;
import ru.kotikov.appliances.repository.TelevisionModelRepo;
import ru.kotikov.appliances.repository.TelevisionRepo;

@Service
public class TelevisionModelsService {

    @Autowired
    private TelevisionModelRepo televisionModelRepo;
    @Autowired
    private TelevisionRepo televisionRepo;

    public TelevisionModel createTelevisionModel(TelevisionModelEntity televisionModel, Long televisionId) {
        TelevisionEntity television = televisionRepo.findById(televisionId).get();
        televisionModel.setTelevision(television);
        return TelevisionModel.toTelevisionModel(televisionModelRepo.save(televisionModel));
    }

    public TelevisionModel inStockTelevisionModel(Long id) {
        TelevisionModelEntity televisionModel = televisionModelRepo.findById(id).get();
        televisionModel.setInStock(!televisionModel.getInStock());
        return TelevisionModel.toTelevisionModel(televisionModelRepo.save(televisionModel));


    }
}
