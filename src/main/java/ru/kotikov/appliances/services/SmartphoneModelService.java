package ru.kotikov.appliances.services;

import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.SmartphoneModelDto;
import ru.kotikov.appliances.entity.SmartphoneModelEntity;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.repository.SmartphoneModelRepo;
import ru.kotikov.appliances.repository.SmartphoneRepo;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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
        if (smartphoneModelRepo.findByName(smartphoneModel.getName()) != null) {
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
        return smartphoneModelRepo.findAll().stream()
                .sorted((Comparator.comparing(SmartphoneModelEntity::getPrice)))
                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(SmartphoneModelDto::toModelDto).collect(Collectors.toList());
    }

    public SmartphoneModelDto searchById(Long id) throws ModelNotFoundException {
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

    public List<SmartphoneModelDto> searchByName(String name) throws ModelNotFoundException {
        return smartphoneModelRepo.findAll().stream()
                .filter(x -> x.getName().equalsIgnoreCase(name))
                .map(SmartphoneModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<SmartphoneModelDto> searchByColor(String color) throws ModelNotFoundException {
        return smartphoneModelRepo.findAll().stream()
                .filter(x -> x.getColor().equalsIgnoreCase(color))
                .map(SmartphoneModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<SmartphoneModelDto> searchByPrice(Double low, Double high) throws ModelNotFoundException {
        return smartphoneModelRepo.findAll().stream()
                .map(SmartphoneModelDto::toModelDto).sorted()
                .filter(x -> (x.getPrice() > low) && (high > x.getPrice())).collect(Collectors.toList());
    }

    public List<SmartphoneModelDto> searchWithFilters(
            String name, Long serialNumber, String color, String size,
            Double lowPrice, Double highPrice, Integer volumeOfMemory, Integer numberOfCameras, Boolean inStock
    ) {
        return smartphoneModelRepo.findAll().stream()
                .map(SmartphoneModelDto::toModelDto).sorted()
                .filter(x -> {
                    if (!name.trim().isEmpty()) return x.getName().equalsIgnoreCase(name);
                    else return true;
                })
                .filter(x -> {
                    if (!(serialNumber == 0)) return Objects.equals(x.getSerialNumber(), serialNumber);
                    else return true;
                })
                .filter(x -> {
                    if (!color.trim().isEmpty()) return x.getColor().equalsIgnoreCase(color);
                    else return true;
                })
                .filter(x -> {
                    if (!size.trim().isEmpty()) return x.getSize().equalsIgnoreCase(size);
                    else return true;
                })
                .filter(x -> (x.getPrice() > lowPrice) && (highPrice > x.getPrice()))
                .filter(x -> {
                    if (!(volumeOfMemory == 0)) return x.getVolumeOfMemory().equals(volumeOfMemory);
                    else return true;
                })
                .filter(x -> {
                    if (!(numberOfCameras == 0)) return x.getNumbersOfCameras().equals(numberOfCameras);
                    else return true;
                })
                .filter(x -> {
                    if (inStock) return x.getInStock().equals(true);
                    else return true;
                })
                .collect(Collectors.toList());
    }
}

