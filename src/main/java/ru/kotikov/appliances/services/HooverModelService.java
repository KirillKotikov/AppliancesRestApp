package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.HooverEntity;
import ru.kotikov.appliances.entities.HooverModelEntity;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.models.HooverModel;
import ru.kotikov.appliances.repository.HooverModelRepo;
import ru.kotikov.appliances.repository.HooverRepo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HooverModelService {
    @Autowired
    private HooverModelRepo hooverModelRepo;
    @Autowired
    private HooverRepo hooverRepo;

    public HooverModel create(HooverModelEntity hooverModel, Long id) throws ModelAlreadyExistException {
        if (hooverModelRepo.findByName(hooverModel.getName()) != null) {
            throw new ModelAlreadyExistException("Модель пылесоса с таким именем уже существует!");
        }
        HooverEntity hoover = hooverRepo.findById(id).get();
        hooverModel.setHoover(hoover);
        return HooverModel.toModel(hooverModelRepo.save(hooverModel));
    }

    public List<HooverModel> getAll() {
        return hooverModelRepo.findAll().stream().sorted((Comparator.comparing(HooverModelEntity::getPrice)))
                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(HooverModel::toModel).collect(Collectors.toList());
    }

    public HooverModel getOne(Long id) throws ModelNotFoundException {
        HooverModelEntity hooverModel = hooverModelRepo.findById(id).get();
        if (hooverModel == null) {
            throw new ModelNotFoundException("Модель пылесоса не найдена!");
        }
        return HooverModel.toModel(hooverModel);
    }

    public HooverModelEntity update(HooverModelEntity hooverModel) throws ModelNotFoundException {
        HooverModelEntity hooverModelEntity = hooverModelRepo.findById(hooverModel.getId()).get();
        if (hooverModelEntity == null) {
            throw new ModelNotFoundException("Модель пылесоса не найдена!");
        }
        return hooverModelRepo.saveAndFlush(hooverModel);
    }

    public Long delete(Long id) throws ModelNotFoundException {
        HooverModelEntity hoover = hooverModelRepo.findById(id).get();
        if (hoover == null) {
            throw new ModelNotFoundException("Модель пылесоса не найдена!");
        }
        hooverModelRepo.deleteById(id);
        return id;
    }
}
