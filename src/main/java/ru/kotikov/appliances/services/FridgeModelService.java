package ru.kotikov.appliances.services;

import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.FridgeModelDto;
import ru.kotikov.appliances.entity.FridgeModelEntity;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.repository.FridgeModelRepo;
import ru.kotikov.appliances.repository.FridgeRepo;

import java.util.List;
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
        if (fridgeModelRepo.getByNameContainingIgnoreCase(fridgeModel.getName()) != null) {
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
        return fridgeModelRepo.findAll().stream().map(FridgeModelDto::toModelDto).collect(Collectors.toList());
    }

    public FridgeModelDto getById(Long id) throws ModelNotFoundException {
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

    public List<FridgeModelDto> getByName(String name) throws ModelNotFoundException {
        return fridgeModelRepo.getByNameContainingIgnoreCase(name).stream()
                .map(FridgeModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<FridgeModelDto> getByColor(String color) throws ModelNotFoundException {
        return fridgeModelRepo.getByColorContainingIgnoreCase(color).stream()
                .map(FridgeModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<FridgeModelDto> getByPrice(Double low, Double high) throws ModelNotFoundException {
        return fridgeModelRepo.getByPriceGreaterThanAndPriceLessThan(low, high).stream()
                .map(FridgeModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<FridgeModelDto> getByParams(
            String name, Long serialNumber, String color, String size,
            Double lowPrice, Double highPrice, Integer numbersOfDoors, String compressorType, Boolean inStock
    ) {
        return fridgeModelRepo.getByParams(
                name, serialNumber, color, size, lowPrice, highPrice, numbersOfDoors, compressorType, inStock
        ).stream().map(FridgeModelDto::toModelDto).sorted().collect(Collectors.toList());
    }
}
