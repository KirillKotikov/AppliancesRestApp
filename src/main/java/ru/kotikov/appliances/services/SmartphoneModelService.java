package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.SmartphoneEntity;
import ru.kotikov.appliances.entities.SmartphoneModelEntity;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.models.HooverModel;
import ru.kotikov.appliances.models.SmartphoneModel;
import ru.kotikov.appliances.repository.SmartphoneModelRepo;
import ru.kotikov.appliances.repository.SmartphoneRepo;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SmartphoneModelService {
    @Autowired
    private SmartphoneModelRepo smartphoneModelRepo;
    @Autowired
    private SmartphoneRepo smartphoneRepo;

    public SmartphoneModel create(SmartphoneModelEntity smartphoneModel, Long id) throws ModelAlreadyExistException {
        if (smartphoneModelRepo.findByName(smartphoneModel.getName()) != null) {
            throw new ModelAlreadyExistException("Модель смартфона с таким именем уже существует!");
        }
        SmartphoneEntity smartphone = smartphoneRepo.findById(id).get();
        smartphoneModel.setSmartphone(smartphone);
        return SmartphoneModel.toModel(smartphoneModelRepo.save(smartphoneModel));
    }

    public List<SmartphoneModel> getAll() {
        return smartphoneModelRepo.findAll().stream()
                .sorted((Comparator.comparing(SmartphoneModelEntity::getPrice)))
                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(SmartphoneModel::toModel).collect(Collectors.toList());
    }

    public SmartphoneModel getOne(Long id) throws ModelNotFoundException {
        SmartphoneModelEntity smartphoneModel = smartphoneModelRepo.findById(id).get();
        if (smartphoneModel == null) {
            throw new ModelNotFoundException("Модель смартфона не найдена!");
        }
        return SmartphoneModel.toModel(smartphoneModel);
    }

    public SmartphoneModelEntity update(SmartphoneModelEntity smartphoneModel) throws ModelNotFoundException {
        SmartphoneModelEntity smartphoneModelEntity = smartphoneModelRepo.findById(smartphoneModel.getId()).get();
        if (smartphoneModelEntity == null) {
            throw new ModelNotFoundException("Модель смартфона не найдена!");
        }
        return smartphoneModelRepo.saveAndFlush(smartphoneModel);
    }

    public Long delete(Long id) throws ModelNotFoundException {
        SmartphoneModelEntity smartphoneModelEntity = smartphoneModelRepo.findById(id).get();
        if (smartphoneModelEntity == null) {
            throw new ModelNotFoundException("Модель смартфона не найдена!");
        }
        smartphoneModelRepo.deleteById(id);
        return id;
    }

    public List<SmartphoneModel> searchByName(String name) throws ModelNotFoundException {
        List<SmartphoneModel> smartphoneModels = smartphoneModelRepo.findAll().stream()
                .filter(x -> x.getName().equalsIgnoreCase(name))
                .map(SmartphoneModel::toModel).sorted().collect(Collectors.toList());
        if (smartphoneModels.size() == 0) throw new ModelNotFoundException("Модель с таким именем не найдена!");
        return smartphoneModels;
    }

    public List<SmartphoneModel> searchByColor(String color) throws ModelNotFoundException {
        List<SmartphoneModel> smartphoneModels = smartphoneModelRepo.findAll().stream()
                .filter(x -> x.getColor().equalsIgnoreCase(color))
                .map(SmartphoneModel::toModel).sorted().collect(Collectors.toList());
        if (smartphoneModels.size() == 0) throw new ModelNotFoundException("Модель с таким цветом не найдена!");
        return smartphoneModels;
    }

    public List<SmartphoneModel> searchByPrice(Double low, Double high) throws ModelNotFoundException {
        List<SmartphoneModel> smartphoneModels = smartphoneModelRepo.findAll().stream()
                .map(SmartphoneModel::toModel).sorted()
                .filter(x -> (x.getPrice() > low) && (high > x.getPrice())).collect(Collectors.toList());
        if (smartphoneModels.size() == 0) throw new ModelNotFoundException("Модель с такой ценой не найдена!");
        return smartphoneModels;
    }

    public List<SmartphoneModel> searchWithFilters(
            String name, Long serialNumber, String color, String size,
            Double lowPrice, Double highPrice, Integer volumeOfMemory, Integer numberOfCameras, Boolean inStock
    ) {
        return smartphoneModelRepo.findAll().stream()
                .map(SmartphoneModel::toModel).sorted()
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

