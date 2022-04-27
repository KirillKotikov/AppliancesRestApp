package ru.kotikov.appliances.services;

import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceModelDto;
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
public class ComputerModelService implements ApplianceModelService {

    private final ComputerModelRepo computerModelRepo;
    private final ComputerRepo computerRepo;

    public ComputerModelService(ComputerModelRepo computerModelRepo, ComputerRepo computerRepo) {
        this.computerModelRepo = computerModelRepo;
        this.computerRepo = computerRepo;
    }

    @Override
    public ApplianceModelDto create(ApplianceModelDto applianceModelDto, Long computerId)
            throws ModelAlreadyExistException, ApplianceNotFoundException {
        if (computerModelRepo.getByNameContainingIgnoreCase(applianceModelDto.getName()) != null) {
            throw new ModelAlreadyExistException("Модель компьютера с таким именем уже существует!");
        } else if (computerRepo.findById(computerId).isPresent()) {
            ComputerModelEntity computerModelEntity = toEntity((ComputerModelDto) applianceModelDto);
            computerModelEntity.setComputer(computerRepo.findById(computerId).get());
            computerModelRepo.save(computerModelEntity);
            return applianceModelDto;
        } else
            throw new ApplianceNotFoundException("Группа компьютеров с id = " + computerId + " для добавления модели не найдена!");
    }

    @Override
    public List<ApplianceModelDto> getAll() {
        return computerModelRepo.findAll().stream().map(ComputerModelDto::toModelDto).collect(Collectors.toList());

    }

    @Override
    public ApplianceModelDto getById(Long id) throws ModelNotFoundException {
        if (computerModelRepo.findById(id).isPresent()) {
            return toModelDto(computerModelRepo.findById(id).get());
        } else throw new ModelNotFoundException("Модель компьютера с id = " + id + " не найдена!");
    }

    @Override
    public ApplianceModelDto update(ApplianceModelDto applianceModelDto) throws ModelNotFoundException {
        if (computerModelRepo.findById(applianceModelDto.getId()).isPresent()) {
            computerModelRepo.saveAndFlush(toEntity((ComputerModelDto) applianceModelDto));
            return applianceModelDto;
        } else throw new ModelNotFoundException("Модель компьютера для изменения (обновления) не найдена!");
    }

    @Override
    public void delete(Long id) throws ModelNotFoundException {
        if (computerModelRepo.findById(id).isPresent()) {
            computerModelRepo.deleteById(id);
        } else throw new ModelNotFoundException("Модель компьютера с id = " + id + " для удаления не найдена!");
    }

    @Override
    public List<ApplianceModelDto> getByName(String name) throws ModelNotFoundException {
        return computerModelRepo.getByNameContainingIgnoreCase(name).stream()
                .map(ComputerModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    @Override
    public List<ApplianceModelDto> getByColor(String color) throws ModelNotFoundException {
        return computerModelRepo.getByColorContainingIgnoreCase(color).stream()
                .map(ComputerModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    @Override
    public List<ApplianceModelDto> getByPrice(Double low, Double high) throws ModelNotFoundException {
        return computerModelRepo.getByPriceGreaterThanAndPriceLessThan(low, high).stream()
                .map(ComputerModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<ApplianceModelDto> getByParams(
            String name, Long serialNumber, String color, String size,
            Double lowPrice, Double highPrice, String category, String processorType, Boolean inStock
    ) {
        return computerModelRepo.getByParams(
                name, serialNumber, color, size, lowPrice, highPrice, category, processorType, inStock
        ).stream().map(ComputerModelDto::toModelDto).sorted().collect(Collectors.toList());
    }
}
