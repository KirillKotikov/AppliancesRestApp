package ru.kotikov.appliances.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceModelDto;
import ru.kotikov.appliances.dto.ComputerModelDto;
import ru.kotikov.appliances.entity.ComputerModelEntity;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.repository.ComputerModelRepo;
import ru.kotikov.appliances.repository.ComputerRepo;

import java.util.ArrayList;
import java.util.List;

import static ru.kotikov.appliances.entity.ComputerModelEntity.toEntity;

@Log4j2
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
    public List<Object> getAll() {
        return new ArrayList<>(computerModelRepo.findAll());
    }

    @Override
    public Object getById(Long id) throws ModelNotFoundException {
        if (computerModelRepo.findById(id).isPresent()) {
            return computerModelRepo.findById(id).get();
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
            log.info("Удалена модель компьютера с id = {}", id);
        } else throw new ModelNotFoundException("Модель компьютера с id = " + id + " для удаления не найдена!");
    }

    @Override
    public List<Object> getByName(String name) throws ModelNotFoundException {
        return new ArrayList<>(computerModelRepo.getByNameContainingIgnoreCase(name));
    }

    @Override
    public List<Object> getByColor(String color) throws ModelNotFoundException {
        return new ArrayList<>(computerModelRepo.getByColorContainingIgnoreCase(color));
    }

    @Override
    public List<Object> getByPrice(Double low, Double high) throws ModelNotFoundException {
        return new ArrayList<>(computerModelRepo.getByPriceGreaterThanAndPriceLessThan(low, high));
    }

    @Override
    public List<Object> getByParams(
            String name, Long serialNumber, String color, String size,
            Double lowPrice, Double highPrice, Object category, Object processorType, Boolean inStock
    ) {
        return new ArrayList<>(computerModelRepo.getByParams(
                name, serialNumber, color, size, lowPrice, highPrice, (String) category, (String) processorType, inStock
        ));
    }
}