package ru.kotikov.appliances.services;

import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.SmartphoneModelDto;
import ru.kotikov.appliances.entity.SmartphoneModelEntity;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.repository.SmartphoneModelRepo;
import ru.kotikov.appliances.repository.SmartphoneRepo;

import java.util.List;
import java.util.stream.Collectors;

import static ru.kotikov.appliances.dto.SmartphoneModelDto.toModelDto;
import static ru.kotikov.appliances.entity.SmartphoneModelEntity.toEntity;

@Service
public class SmartphoneModelService {
    private final SmartphoneModelRepo smartphoneModelRepo;
    private final SmartphoneRepo smartphoneRepo;

    public SmartphoneModelService(SmartphoneModelRepo smartphoneModelRepo, SmartphoneRepo smartphoneRepo) {
        this.smartphoneModelRepo = smartphoneModelRepo;
        this.smartphoneRepo = smartphoneRepo;
    }

    public SmartphoneModelDto create(SmartphoneModelDto smartphoneModel, Long smartphoneId)
            throws ModelAlreadyExistException, ApplianceNotFoundException {
        if (smartphoneModelRepo.getByNameContainingIgnoreCase(smartphoneModel.getName()) != null) {
            throw new ModelAlreadyExistException("Модель смартфона с таким именем уже существует!");
        } else if (smartphoneRepo.findById(smartphoneId).isPresent()) {
            SmartphoneModelEntity smartphoneModelEntity = toEntity(smartphoneModel);
            smartphoneModelEntity.setSmartphone(smartphoneRepo.findById(smartphoneId).get());
            smartphoneModelRepo.save(smartphoneModelEntity);
            return smartphoneModel;
        } else
            throw new ApplianceNotFoundException("Группа смартфонов с id = " + smartphoneId + " для добавления модели не найдена!");
    }

    public List<SmartphoneModelDto> getAll() {
        return smartphoneModelRepo.findAll().stream().map(SmartphoneModelDto::toModelDto).collect(Collectors.toList());
    }

    public SmartphoneModelDto getById(Long id) throws ModelNotFoundException {
        if (smartphoneModelRepo.findById(id).isPresent()) {
            return toModelDto(smartphoneModelRepo.findById(id).get());
        } else throw new ModelNotFoundException("Модель смартфона c id = " + id + " не найдена!");
    }

    public SmartphoneModelDto update(SmartphoneModelDto smartphoneModel) throws ModelNotFoundException {
        if (smartphoneModelRepo.findById(smartphoneModel.getId()).isPresent()) {
            smartphoneModelRepo.saveAndFlush(toEntity(smartphoneModel));
            return smartphoneModel;
        } else throw new ModelNotFoundException("Модель смартфона для изменения (обновления) не найдена!");
    }

    public void delete(Long id) throws ModelNotFoundException {
        if (smartphoneModelRepo.findById(id).isPresent()) {
            smartphoneModelRepo.deleteById(id);
        } else throw new ModelNotFoundException("Модель смартфона с id = " + id + " для удаления не найдена!");
    }

    public List<SmartphoneModelDto> getByName(String name) throws ModelNotFoundException {
        return smartphoneModelRepo.getByNameContainingIgnoreCase(name).stream()
                .map(SmartphoneModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<SmartphoneModelDto> getByColor(String color) throws ModelNotFoundException {
        return smartphoneModelRepo.getByColorContainingIgnoreCase(color).stream()
                .map(SmartphoneModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<SmartphoneModelDto> getByPrice(Double low, Double high) throws ModelNotFoundException {
        return smartphoneModelRepo.getByPriceGreaterThanAndPriceLessThan(low, high).stream()
                .map(SmartphoneModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<SmartphoneModelDto> getByParams(
            String name, Long serialNumber, String color, String size,
            Double lowPrice, Double highPrice, Integer volumeOfMemory, Integer numberOfCameras, Boolean inStock
    ) {
        return smartphoneModelRepo.getByParams(
                name, serialNumber, color, size, lowPrice, highPrice, volumeOfMemory, numberOfCameras, inStock
        ).stream().map(SmartphoneModelDto::toModelDto).sorted().collect(Collectors.toList());
    }
}

