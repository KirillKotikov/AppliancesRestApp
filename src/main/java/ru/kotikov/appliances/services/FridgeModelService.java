package ru.kotikov.appliances.services;

import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.FridgeModelDto;
import ru.kotikov.appliances.entity.FridgeModelEntity;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.repository.FridgeModelRepo;
import ru.kotikov.appliances.repository.FridgeRepo;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.kotikov.appliances.dto.FridgeModelDto.toModelDto;
import static ru.kotikov.appliances.entity.FridgeModelEntity.toEntity;

@Service
public class FridgeModelService {
    private final FridgeModelRepo fridgeModelRepo;
    private final FridgeRepo fridgeRepo;

    public FridgeModelService(FridgeModelRepo fridgeModelRepo, FridgeRepo fridgeRepo) {
        this.fridgeModelRepo = fridgeModelRepo;
        this.fridgeRepo = fridgeRepo;
    }

    public FridgeModelDto create(FridgeModelDto fridgeModel, Long fridgeId)
            throws ModelAlreadyExistException, ApplianceNotFoundException {
        if (fridgeModelRepo.findByName(fridgeModel.getName()) != null) {
            throw new ModelAlreadyExistException("Модель холодильника с таким именем уже существует!");
        } else if (fridgeRepo.findById(fridgeId).isPresent()) {
            FridgeModelEntity fridgeModelEntity = toEntity(fridgeModel);
            fridgeModelEntity.setFridge(fridgeRepo.findById(fridgeId).get());
            fridgeModelRepo.save(fridgeModelEntity);
            return fridgeModel;
        } else
            throw new ApplianceNotFoundException("Группа холодильников с id = " + fridgeId + " для добавления модели не найдена!");
    }

    public List<FridgeModelDto> getAll() {
        return fridgeModelRepo.findAll().stream()
                .sorted((Comparator.comparing(FridgeModelEntity::getPrice)))
                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(FridgeModelDto::toModelDto).collect(Collectors.toList());
    }

    public FridgeModelDto searchById(Long id) throws ModelNotFoundException {
        if (fridgeModelRepo.findById(id).isPresent()) {
            return toModelDto(fridgeModelRepo.findById(id).get());
        } else throw new ModelNotFoundException("Модель холодильника c id = " + id + " не найдена!");
    }

    public FridgeModelDto update(FridgeModelDto fridgeModel) throws ModelNotFoundException {
        if (fridgeModelRepo.findById(fridgeModel.getId()).isPresent()) {
            fridgeModelRepo.saveAndFlush(toEntity(fridgeModel));
            return fridgeModel;
        } else throw new ModelNotFoundException("Модель холодильника для изменения (обновления) не найдена!");
    }

    public void delete(Long id) throws ModelNotFoundException {
        if (fridgeModelRepo.findById(id).isPresent()) {
            fridgeModelRepo.deleteById(id);
        } else throw new ModelNotFoundException("Модель холодильника с id = " + id + " для удаления не найдена!");
    }

    public List<FridgeModelDto> searchByName(String name) throws ModelNotFoundException {
        return fridgeModelRepo.findAll().stream()
                .filter(x -> x.getName().equalsIgnoreCase(name))
                .map(FridgeModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<FridgeModelDto> searchByColor(String color) throws ModelNotFoundException {
        return fridgeModelRepo.findAll().stream()
                .filter(x -> x.getColor().equalsIgnoreCase(color))
                .map(FridgeModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<FridgeModelDto> searchByPrice(Double low, Double high) throws ModelNotFoundException {
        return fridgeModelRepo.findAll().stream()
                .map(FridgeModelDto::toModelDto).sorted()
                .filter(x -> (x.getPrice() > low) && (high > x.getPrice())).collect(Collectors.toList());
    }

    public List<FridgeModelDto> searchWithFilters(
            String name, Long serialNumber, String color, String size,
            Double lowPrice, Double highPrice, Integer numbersOfDoors, String compressorType, Boolean inStock
    ) {
        return fridgeModelRepo.findAll().stream()
                .map(FridgeModelDto::toModelDto).sorted()
                .filter(x -> {
                    if (!name.trim().isEmpty()) return x.getName().equalsIgnoreCase(name);
                    else return true;
                })
                .filter(x -> {
                    if (serialNumber != 0) return Objects.equals(x.getSerialNumber(), serialNumber);
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
                    if (numbersOfDoors != 0) return Objects.equals(x.getNumbersOfDoors(), numbersOfDoors);
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
