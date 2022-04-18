package ru.kotikov.appliances.services;

import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.TelevisionModelDto;
import ru.kotikov.appliances.entity.TelevisionModelEntity;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.repository.TelevisionModelRepo;
import ru.kotikov.appliances.repository.TelevisionRepo;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.kotikov.appliances.dto.TelevisionModelDto.toModelDto;
import static ru.kotikov.appliances.entity.TelevisionModelEntity.toEntity;

@Service
public class TelevisionModelService {

    private final TelevisionModelRepo televisionModelRepo;
    private final TelevisionRepo televisionRepo;

    public TelevisionModelService(TelevisionModelRepo televisionModelRepo, TelevisionRepo televisionRepo) {
        this.televisionModelRepo = televisionModelRepo;
        this.televisionRepo = televisionRepo;
    }

    public TelevisionModelDto create(TelevisionModelDto televisionModel, Long televisionId)
            throws ModelAlreadyExistException, ApplianceNotFoundException {
        if (televisionModelRepo.getByNameContainingIgnoreCase(televisionModel.getName()) != null) {
            throw new ModelAlreadyExistException("Модель телевизора с таким именем уже существует!");
        } else if (televisionRepo.findById(televisionId).isPresent()) {
            TelevisionModelEntity televisionModelEntity = toEntity(televisionModel);
            televisionModelEntity.setTelevision(televisionRepo.findById(televisionId).get());
            televisionModelRepo.save(televisionModelEntity);
            return televisionModel;
        } else
            throw new ApplianceNotFoundException("Группа телеизоров с id = " + televisionId + " для добавления модели не найдена!");
    }

    public List<TelevisionModelDto> getAll() {
        return televisionModelRepo.findAll().stream()
                .sorted((Comparator.comparing(TelevisionModelEntity::getPrice)))
                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(TelevisionModelDto::toModelDto).collect(Collectors.toList());
    }

    public TelevisionModelDto searchById(Long id) throws ModelNotFoundException {
        if (televisionModelRepo.findById(id).isPresent()) {
            return toModelDto(televisionModelRepo.findById(id).get());
        } else throw new ModelNotFoundException("Модель телевизора с id = " + id + " не найдена!");
    }

    public TelevisionModelDto update(TelevisionModelDto televisionModel) throws ModelNotFoundException {
        if (televisionModelRepo.findById(televisionModel.getId()).isPresent()) {
            televisionModelRepo.saveAndFlush(toEntity(televisionModel));
            return televisionModel;
        } else throw new ModelNotFoundException("Модель телевизора для изменения (обновления) не найдена!");
    }

    public void delete(Long id) throws ModelNotFoundException {
        if (televisionModelRepo.findById(id).isPresent()) {
            televisionModelRepo.deleteById(id);
        } else throw new ModelNotFoundException("Модель телевизора с id = " + id + " для удаления не найдена!");
    }

    public List<TelevisionModelDto> searchByName(String name) throws ModelNotFoundException {
        return televisionModelRepo.getByNameContainingIgnoreCase(name).stream()
                .map(TelevisionModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<TelevisionModelDto> searchByColor(String color) throws ModelNotFoundException {
        return televisionModelRepo.getByColorContainingIgnoreCase(color).stream()
                .map(TelevisionModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<TelevisionModelDto> searchByPrice(Double low, Double high) throws ModelNotFoundException {
        return televisionModelRepo.getByPriceGreaterThanAndPriceLessThan(low, high).stream()
                .map(TelevisionModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<TelevisionModelDto> searchWithFilters(
            String name, Long serialNumber, String color, String size,
            Double lowPrice, Double highPrice, String category, String technology, Boolean inStock
    ) {
        return televisionModelRepo.getByParams(
                        name, serialNumber, color, size, lowPrice, highPrice, category, technology, inStock
                ).stream()
                .map(TelevisionModelDto::toModelDto).sorted().collect(Collectors.toList());
    }
}
