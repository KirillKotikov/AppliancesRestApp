package ru.kotikov.appliances.services;

import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ComputerModelDto;
import ru.kotikov.appliances.entity.ComputerModelEntity;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.repository.ComputerModelRepo;
import ru.kotikov.appliances.repository.ComputerRepo;

import java.util.List;
import java.util.stream.Collectors;

import static ru.kotikov.appliances.dto.ComputerModelDto.toModelDto;
import static ru.kotikov.appliances.entity.ComputerModelEntity.toEntity;

@Service
public class ComputerModelService {

    private final ComputerModelRepo computerModelRepo;
    private final ComputerRepo computerRepo;

    public ComputerModelService(ComputerModelRepo computerModelRepo, ComputerRepo computerRepo) {
        this.computerModelRepo = computerModelRepo;
        this.computerRepo = computerRepo;
    }

    public ComputerModelDto create(ComputerModelDto computerModel, Long computerId)
            throws ModelAlreadyExistException, ApplianceNotFoundException {
        if (computerModelRepo.getByNameContainingIgnoreCase(computerModel.getName()) != null) {
            throw new ModelAlreadyExistException("Модель компьютера с таким именем уже существует!");
        } else if (computerRepo.findById(computerId).isPresent()) {
            ComputerModelEntity computerModelEntity = toEntity(computerModel);
            computerModelEntity.setComputer(computerRepo.findById(computerId).get());
            computerModelRepo.save(computerModelEntity);
            return computerModel;
        } else
            throw new ApplianceNotFoundException("Группа компьютеров с id = " + computerId + " для добавления модели не найдена!");
    }

    public List<ComputerModelDto> getAll() {
        return computerModelRepo.findAll().stream()
                .map(ComputerModelDto::toModelDto).collect(Collectors.toList());

    }

    public ComputerModelDto searchById(Long id) throws ModelNotFoundException {
        if (computerModelRepo.findById(id).isPresent()) {
            return toModelDto(computerModelRepo.findById(id).get());
        } else throw new ModelNotFoundException("Модель компьютера с id = " + id + " не найдена!");
    }

    public ComputerModelDto update(ComputerModelDto computerModel) throws ModelNotFoundException {
        if (computerModelRepo.findById(computerModel.getId()).isPresent()) {
            computerModelRepo.saveAndFlush(toEntity(computerModel));
            return computerModel;
        } else throw new ModelNotFoundException("Модель компьютера для изменения (обновления) не найдена!");
    }

    public void delete(Long id) throws ModelNotFoundException {
        if (computerModelRepo.findById(id).isPresent()) {
            computerModelRepo.deleteById(id);
        } else throw new ModelNotFoundException("Модель компьютера с id = " + id + " для удаления не найдена!");
    }

    public List<ComputerModelDto> searchByName(String name) throws ModelNotFoundException {
        return computerModelRepo.getByNameContainingIgnoreCase(name).stream()
                .map(ComputerModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<ComputerModelDto> searchByColor(String color) throws ModelNotFoundException {
        return computerModelRepo.getByColorContainingIgnoreCase(color).stream()
                .map(ComputerModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<ComputerModelDto> searchByPrice(Double low, Double high) throws ModelNotFoundException {
        return computerModelRepo.getByPriceGreaterThanAndPriceLessThan(low, high).stream()
                .map(ComputerModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<ComputerModelDto> searchWithFilters(
            String name, Long serialNumber, String color, String size,
            Double lowPrice, Double highPrice, String category, String processorType, Boolean inStock
    ) {
        if (name != null) name = name.toUpperCase();
        if (color != null) color = color.toUpperCase();
        if (size != null) size = size.toUpperCase();
        if (category != null) category = category.toUpperCase();
        if (processorType != null) processorType = processorType.toUpperCase();
        return computerModelRepo.getByParams(
                name, serialNumber, color, size, lowPrice, highPrice, category, processorType, inStock
        ).stream().map(ComputerModelDto::toModelDto).sorted().collect(Collectors.toList());
    }
}
