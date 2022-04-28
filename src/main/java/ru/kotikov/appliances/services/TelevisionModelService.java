package ru.kotikov.appliances.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceModelDto;
import ru.kotikov.appliances.dto.TelevisionModelDto;
import ru.kotikov.appliances.entity.TelevisionModelEntity;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.repository.TelevisionModelRepo;
import ru.kotikov.appliances.repository.TelevisionRepo;

import java.util.ArrayList;
import java.util.List;

import static ru.kotikov.appliances.entity.TelevisionModelEntity.toEntity;

@Log4j2
@Service
public class TelevisionModelService implements ApplianceModelService {

    private final TelevisionModelRepo televisionModelRepo;
    private final TelevisionRepo televisionRepo;

    public TelevisionModelService(TelevisionModelRepo televisionModelRepo, TelevisionRepo televisionRepo) {
        this.televisionModelRepo = televisionModelRepo;
        this.televisionRepo = televisionRepo;
    }

    @Override
    public ApplianceModelDto create(ApplianceModelDto televisionModel, Long televisionId)
            throws ModelAlreadyExistException, ApplianceNotFoundException {
        if (televisionModelRepo.getByNameContainingIgnoreCase(televisionModel.getName()) != null) {
            throw new ModelAlreadyExistException("Модель телевизора с таким именем уже существует!");
        } else if (televisionRepo.findById(televisionId).isPresent()) {
            TelevisionModelEntity televisionModelEntity = toEntity((TelevisionModelDto) televisionModel);
            televisionModelEntity.setTelevision(televisionRepo.findById(televisionId).get());
            televisionModelRepo.save(televisionModelEntity);
            return televisionModel;
        } else
            throw new ApplianceNotFoundException("Группа телеизоров с id = " + televisionId + " для добавления модели не найдена!");
    }

    @Override
    public List<Object> getAll() {
        return new ArrayList<>(televisionModelRepo.findAll());
    }

    @Override
    public Object getById(Long id) throws ModelNotFoundException {
        if (televisionModelRepo.findById(id).isPresent()) {
            return televisionModelRepo.findById(id).get();
        } else throw new ModelNotFoundException("Модель телевизора с id = " + id + " не найдена!");
    }

    @Override
    public ApplianceModelDto update(ApplianceModelDto televisionModel) throws ModelNotFoundException {
        if (televisionModelRepo.findById(televisionModel.getId()).isPresent()) {
            televisionModelRepo.saveAndFlush(toEntity((TelevisionModelDto) televisionModel));
            return televisionModel;
        } else throw new ModelNotFoundException("Модель телевизора для изменения (обновления) не найдена!");
    }

    @Override
    public void delete(Long id) throws ModelNotFoundException {
        if (televisionModelRepo.findById(id).isPresent()) {
            televisionModelRepo.deleteById(id);
            log.info("Удалена модель телевизора с id = {}", id);
        } else throw new ModelNotFoundException("Модель телевизора с id = " + id + " для удаления не найдена!");
    }

    @Override
    public List<Object> getByName(String name) throws ModelNotFoundException {
        return new ArrayList<>(televisionModelRepo.getByNameContainingIgnoreCase(name));
    }

    @Override
    public List<Object> getByColor(String color) throws ModelNotFoundException {
        return new ArrayList<>(televisionModelRepo.getByColorContainingIgnoreCase(color));
    }

    @Override
    public List<Object> getByPrice(Double low, Double high) throws ModelNotFoundException {
        return new ArrayList<>(televisionModelRepo.getByPriceGreaterThanAndPriceLessThan(low, high));
    }

    @Override
    public List<Object> getByParams(
            String name, Long serialNumber, String color, String size,
            Double lowPrice, Double highPrice, Object category, Object technology, Boolean inStock
    ) {
        return new ArrayList<>(televisionModelRepo.getByParams(
                name, serialNumber, color, size, lowPrice, highPrice, (String) category, (String) technology, inStock
        ));
    }
}