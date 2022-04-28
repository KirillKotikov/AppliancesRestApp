package ru.kotikov.appliances.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceModelDto;
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

@Log4j2
@Service
public class FridgeModelService implements ApplianceModelService {
    private final FridgeModelRepo fridgeModelRepo;
    private final FridgeRepo fridgeRepo;

    public FridgeModelService(FridgeModelRepo fridgeModelRepo, FridgeRepo fridgeRepo) {
        this.fridgeModelRepo = fridgeModelRepo;
        this.fridgeRepo = fridgeRepo;
    }

    @Override
    public ApplianceModelDto create(ApplianceModelDto applianceModelDto, Long fridgeId)
            throws ModelAlreadyExistException, ApplianceNotFoundException {
        if (fridgeModelRepo.getByNameContainingIgnoreCase(applianceModelDto.getName()) != null) {
            throw new ModelAlreadyExistException("Модель холодильника с таким именем уже существует!");
        } else if (fridgeRepo.findById(fridgeId).isPresent()) {
            FridgeModelEntity fridgeModelEntity = toEntity((FridgeModelDto) applianceModelDto);
            fridgeModelEntity.setFridge(fridgeRepo.findById(fridgeId).get());
            fridgeModelRepo.save(fridgeModelEntity);
            return applianceModelDto;
        } else
            throw new ApplianceNotFoundException("Группа холодильников с id = " + fridgeId + " для добавления модели не найдена!");
    }

    @Override
    public List<ApplianceModelDto> getAll() {
        return fridgeModelRepo.findAll().stream().map(FridgeModelDto::toModelDto).collect(Collectors.toList());
    }

    @Override
    public ApplianceModelDto getById(Long id) throws ModelNotFoundException {
        if (fridgeModelRepo.findById(id).isPresent()) {
            return toModelDto(fridgeModelRepo.findById(id).get());
        } else throw new ModelNotFoundException("Модель холодильника c id = " + id + " не найдена!");
    }

    @Override
    public ApplianceModelDto update(ApplianceModelDto fridgeModel) throws ModelNotFoundException {
        if (fridgeModelRepo.findById(fridgeModel.getId()).isPresent()) {
            fridgeModelRepo.saveAndFlush(toEntity((FridgeModelDto) fridgeModel));
            return fridgeModel;
        } else throw new ModelNotFoundException("Модель холодильника для изменения (обновления) не найдена!");
    }

    @Override
    public void delete(Long id) throws ModelNotFoundException {
        if (fridgeModelRepo.findById(id).isPresent()) {
            fridgeModelRepo.deleteById(id);
            log.info("Удалена модель холодильника с id = {}", id);
        } else throw new ModelNotFoundException("Модель холодильника с id = " + id + " для удаления не найдена!");
    }

    @Override
    public List<ApplianceModelDto> getByName(String name) throws ModelNotFoundException {
        return fridgeModelRepo.getByNameContainingIgnoreCase(name).stream()
                .map(FridgeModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    @Override
    public List<ApplianceModelDto> getByColor(String color) throws ModelNotFoundException {
        return fridgeModelRepo.getByColorContainingIgnoreCase(color).stream()
                .map(FridgeModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    @Override
    public List<ApplianceModelDto> getByPrice(Double low, Double high) throws ModelNotFoundException {
        return fridgeModelRepo.getByPriceGreaterThanAndPriceLessThan(low, high).stream()
                .map(FridgeModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    @Override
    public List<ApplianceModelDto> getByParams(
            String name, Long serialNumber, String color, String size,
            Double lowPrice, Double highPrice, Object numbersOfDoors, Object compressorType, Boolean inStock
    ) {
        return fridgeModelRepo.getByParams(
                name, serialNumber, color, size, lowPrice, highPrice, (Integer) numbersOfDoors,(String) compressorType, inStock
        ).stream().map(FridgeModelDto::toModelDto).sorted().collect(Collectors.toList());
    }
}