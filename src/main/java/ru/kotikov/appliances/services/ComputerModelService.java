package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.ComputerEntity;
import ru.kotikov.appliances.entities.ComputerModelEntity;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.models.ComputerModel;
import ru.kotikov.appliances.repository.ComputerModelRepo;
import ru.kotikov.appliances.repository.ComputerRepo;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ComputerModelService {

    @Autowired
    private ComputerModelRepo computerModelRepo;
    @Autowired
    private ComputerRepo computerRepo;

    public ComputerModel create(ComputerModelEntity computerModelEntity, Long computerId) throws ModelAlreadyExistException {
        if (computerModelRepo.findByName(computerModelEntity.getName()) != null) {
            throw new ModelAlreadyExistException("Модель компьютера с таким именем уже существует!");
        }
        ComputerEntity computerEntity = computerRepo.findById(computerId).get();
        computerModelEntity.setComputer(computerEntity);
        return ComputerModel.toModel(computerModelRepo.save(computerModelEntity));
    }

    public List<ComputerModel> getAll() {
        return computerModelRepo.findAll().stream()
                .sorted((Comparator.comparing(ComputerModelEntity::getPrice)))
                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(ComputerModel::toModel).collect(Collectors.toList());
    }

    public ComputerModel getOne(Long id) throws ModelNotFoundException {
        ComputerModelEntity computerModelEntity = computerModelRepo.findById(id).get();
        if (computerModelEntity == null) {
            throw new ModelNotFoundException("Модель компьютера не найдена!");
        }
        return ComputerModel.toModel(computerModelEntity);
    }

    public ComputerModelEntity update(ComputerModelEntity computerModelEntity) throws ModelNotFoundException {
        ComputerModelEntity computerModel = computerModelRepo.findById(computerModelEntity.getId()).get();
        if (computerModel == null) {
            throw new ModelNotFoundException("Модель компьютера не найдена!");
        }
        return computerModelRepo.saveAndFlush(computerModelEntity);
    }

    public Long delete(Long id) throws ModelNotFoundException {
        ComputerModelEntity computerModelEntity = computerModelRepo.findById(id).get();
        if (computerModelEntity == null) {
            throw new ModelNotFoundException("Модель компьютера не найдена!");
        }
        computerModelRepo.deleteById(id);
        return id;
    }

    public List<ComputerModel> searchByName(String name) throws ModelNotFoundException {
        List<ComputerModel> computerModels = computerModelRepo.findAll().stream()
                .filter(x -> x.getName().equalsIgnoreCase(name))
                .map(ComputerModel::toModel).sorted().collect(Collectors.toList());
        if (computerModels.size() == 0) throw new ModelNotFoundException("Модель с таким именем не найдена!");
        return computerModels;
    }

    public List<ComputerModel> searchByColor(String color) throws ModelNotFoundException {
        List<ComputerModel> computerModels = computerModelRepo.findAll().stream()
                .filter(x -> x.getColor().equalsIgnoreCase(color))
                .map(ComputerModel::toModel).sorted().collect(Collectors.toList());
        if (computerModels.size() == 0) throw new ModelNotFoundException("Модель с таким цветом не найдена!");
        return computerModels;
    }

    public List<ComputerModel> searchByPrice(Double low, Double high) throws ModelNotFoundException {
        List<ComputerModel> computerModels = computerModelRepo.findAll().stream()
                .map(ComputerModel::toModel).sorted()
                .filter(x -> (x.getPrice() > low) && (high > x.getPrice())).collect(Collectors.toList());
        if (computerModels.size() == 0) throw new ModelNotFoundException("Модель с такой ценой не найдена!");
        return computerModels;
    }

    public List<ComputerModel> searchWithFilters(
            String name, Long serialNumber, String color, String size,
            Double lowPrice, Double highPrice, String category, String processorType, Boolean inStock
    ) {
        return computerModelRepo.findAll().stream()
                .map(ComputerModel::toModel).sorted()
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
                    if (!category.trim().isEmpty()) return x.getCategory().equalsIgnoreCase(category);
                    else return true;
                })
                .filter(x -> {
                    if (!processorType.trim().isEmpty()) return x.getProcessorType().equalsIgnoreCase(processorType);
                    else return true;
                })
                .filter(x -> {
                    if (inStock) return x.getInStock().equals(true);
                    else return true;
                })
                .collect(Collectors.toList());
    }
}
