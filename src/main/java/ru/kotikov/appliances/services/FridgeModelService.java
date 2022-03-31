package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.FridgeEntity;
import ru.kotikov.appliances.entities.FridgeModelEntity;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.models.ComputerModel;
import ru.kotikov.appliances.models.FridgeModel;
import ru.kotikov.appliances.repository.FridgeModelRepo;
import ru.kotikov.appliances.repository.FridgeRepo;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FridgeModelService {
    @Autowired
    private FridgeModelRepo fridgeModelRepo;
    @Autowired
    private FridgeRepo fridgeRepo;

    public FridgeModel create(FridgeModelEntity fridgeModel, Long id) throws ModelAlreadyExistException {
        if (fridgeModelRepo.findByName(fridgeModel.getName()) != null) {
            throw new ModelAlreadyExistException("Модель холодильника с таким именем уже существует!");
        }
        FridgeEntity fridge = fridgeRepo.findById(id).get();
        fridgeModel.setFridge(fridge);
        return FridgeModel.toModel(fridgeModelRepo.save(fridgeModel));
    }

    public List<FridgeModel> getAll() {
        return fridgeModelRepo.findAll().stream()
                .sorted((Comparator.comparing(FridgeModelEntity::getPrice)))
                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(FridgeModel::toModel).collect(Collectors.toList());
    }

    public FridgeModel getOne(Long id) throws ModelNotFoundException {
        FridgeModelEntity fridgeModel = fridgeModelRepo.findById(id).get();
        if (fridgeModel == null) {
            throw new ModelNotFoundException("Модель холодильника не найдена!");
        }
        return FridgeModel.toModel(fridgeModel);
    }

    public FridgeModelEntity update(FridgeModelEntity fridgeModel) throws ModelNotFoundException {
        FridgeModelEntity fridgeModelEntity = fridgeModelRepo.findById(fridgeModel.getId()).get();
        if (fridgeModelEntity == null) {
            throw new ModelNotFoundException("Модель холодильника не найдена!");
        }
        return fridgeModelRepo.saveAndFlush(fridgeModel);
    }

    public Long delete(Long id) throws ModelNotFoundException {
        FridgeModelEntity fridge = fridgeModelRepo.findById(id).get();
        if (fridge == null) {
            throw new ModelNotFoundException("Модель холодильника не найдена!");
        }
        fridgeModelRepo.deleteById(id);
        return id;
    }

    public List<FridgeModel> searchByName(String name) throws ModelNotFoundException {
        List<FridgeModel> fridgeModels = fridgeModelRepo.findAll().stream()
                .filter(x -> x.getName().equalsIgnoreCase(name))
                .map(FridgeModel::toModel).sorted().collect(Collectors.toList());
        if (fridgeModels.size() == 0) throw new ModelNotFoundException("Модель с таким именем не найдена!");
        return fridgeModels;
    }

    public List<FridgeModel> searchByColor(String color) throws ModelNotFoundException {
        List<FridgeModel> fridgeModels = fridgeModelRepo.findAll().stream()
                .filter(x -> x.getColor().equalsIgnoreCase(color))
                .map(FridgeModel::toModel).sorted().collect(Collectors.toList());
        if (fridgeModels.size() == 0) throw new ModelNotFoundException("Модель с таким цветом не найдена!");
        return fridgeModels;
    }

    public List<FridgeModel> searchByPrice(Double low, Double high) throws ModelNotFoundException {
        List<FridgeModel> fridgeModels = fridgeModelRepo.findAll().stream()
                .map(FridgeModel::toModel).sorted()
                .filter(x -> (x.getPrice() > low) && (high > x.getPrice())).collect(Collectors.toList());
        if (fridgeModels.size() == 0) throw new ModelNotFoundException("Модель с такой ценой не найдена!");
        return fridgeModels;
    }

    public List<FridgeModel> searchWithFilters(
            String name, Long serialNumber, String color, String size,
            Double lowPrice, Double highPrice, Integer numberOfDoors, String compressorType, Boolean inStock
    ) {
        return fridgeModelRepo.findAll().stream()
                .map(FridgeModel::toModel).sorted()
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
                    if (!(numberOfDoors == 0)) return x.getNumbersOfDoors().equals(numberOfDoors);
                    else return true;
                })
                .filter(x -> {
                    if (!compressorType.trim().isEmpty()) return x.getCompressorType().equalsIgnoreCase(compressorType);
                    else return true;
                })
                .filter(x -> {
                    if (inStock) return x.getInStock().equals(true);
                    else return true;
                })
                .collect(Collectors.toList());
    }
}
