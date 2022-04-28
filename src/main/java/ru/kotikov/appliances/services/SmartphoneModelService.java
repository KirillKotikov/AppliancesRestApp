package ru.kotikov.appliances.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceModelDto;
import ru.kotikov.appliances.dto.SmartphoneModelDto;
import ru.kotikov.appliances.entity.SmartphoneModelEntity;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.repository.SmartphoneModelRepo;
import ru.kotikov.appliances.repository.SmartphoneRepo;

import java.util.ArrayList;
import java.util.List;

import static ru.kotikov.appliances.entity.SmartphoneModelEntity.toEntity;

@Log4j2
@Service
public class SmartphoneModelService implements ApplianceModelService {
    private final SmartphoneModelRepo smartphoneModelRepo;
    private final SmartphoneRepo smartphoneRepo;

    public SmartphoneModelService(SmartphoneModelRepo smartphoneModelRepo, SmartphoneRepo smartphoneRepo) {
        this.smartphoneModelRepo = smartphoneModelRepo;
        this.smartphoneRepo = smartphoneRepo;
    }

    @Override
    public ApplianceModelDto create(ApplianceModelDto smartphoneModel, Long smartphoneId)
            throws ModelAlreadyExistException, ApplianceNotFoundException {
        if (smartphoneModelRepo.getByNameContainingIgnoreCase(smartphoneModel.getName()) != null) {
            throw new ModelAlreadyExistException("Модель смартфона с таким именем уже существует!");
        } else if (smartphoneRepo.findById(smartphoneId).isPresent()) {
            SmartphoneModelEntity smartphoneModelEntity = toEntity((SmartphoneModelDto) smartphoneModel);
            smartphoneModelEntity.setSmartphone(smartphoneRepo.findById(smartphoneId).get());
            smartphoneModelRepo.save(smartphoneModelEntity);
            return smartphoneModel;
        } else
            throw new ApplianceNotFoundException("Группа смартфонов с id = " + smartphoneId + " для добавления модели не найдена!");
    }

    @Override
    public List<Object> getAll() {
        return new ArrayList<>(smartphoneModelRepo.findAll());
    }

    @Override
    public Object getById(Long id) throws ModelNotFoundException {
        if (smartphoneModelRepo.findById(id).isPresent()) {
            return smartphoneModelRepo.findById(id).get();
        } else throw new ModelNotFoundException("Модель смартфона c id = " + id + " не найдена!");
    }

    @Override
    public ApplianceModelDto update(ApplianceModelDto smartphoneModel) throws ModelNotFoundException {
        if (smartphoneModelRepo.findById(smartphoneModel.getId()).isPresent()) {
            smartphoneModelRepo.saveAndFlush(toEntity((SmartphoneModelDto) smartphoneModel));
            return smartphoneModel;
        } else throw new ModelNotFoundException("Модель смартфона для изменения (обновления) не найдена!");
    }

    @Override
    public void delete(Long id) throws ModelNotFoundException {
        if (smartphoneModelRepo.findById(id).isPresent()) {
            smartphoneModelRepo.deleteById(id);
            log.info("Удалена модель телефона с id = {}", id);
        } else throw new ModelNotFoundException("Модель смартфона с id = " + id + " для удаления не найдена!");
    }

    @Override
    public List<Object> getByName(String name) throws ModelNotFoundException {
        return new ArrayList<>(smartphoneModelRepo.getByNameContainingIgnoreCase(name));
    }

    @Override
    public List<Object> getByColor(String color) throws ModelNotFoundException {
        return new ArrayList<>(smartphoneModelRepo.getByColorContainingIgnoreCase(color));
    }

    @Override
    public List<Object> getByPrice(Double low, Double high) throws ModelNotFoundException {
        return new ArrayList<>(smartphoneModelRepo.getByPriceGreaterThanAndPriceLessThan(low, high));
    }

    @Override
    public List<Object> getByParams(
            String name, Long serialNumber, String color, String size,
            Double lowPrice, Double highPrice, Object volumeOfMemory, Object numberOfCameras, Boolean inStock
    ) {
        return new ArrayList<>(smartphoneModelRepo.getByParams(
                name, serialNumber, color, size, lowPrice, highPrice, (Integer) volumeOfMemory, (Integer) numberOfCameras, inStock
        ));
    }
}