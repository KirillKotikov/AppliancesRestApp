package ru.kotikov.appliances.services;

import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceModelDto;
import ru.kotikov.appliances.dto.HooverModelDto;
import ru.kotikov.appliances.entity.HooverModelEntity;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.repository.HooverModelRepo;
import ru.kotikov.appliances.repository.HooverRepo;

import java.util.List;
import java.util.stream.Collectors;

import static ru.kotikov.appliances.dto.HooverModelDto.toModelDto;
import static ru.kotikov.appliances.entity.HooverModelEntity.toEntity;

@Service
public class HooverModelService implements ApplianceModelService {
    private final HooverModelRepo hooverModelRepo;
    private final HooverRepo hooverRepo;

    public HooverModelService(HooverModelRepo hooverModelRepo, HooverRepo hooverRepo) {
        this.hooverModelRepo = hooverModelRepo;
        this.hooverRepo = hooverRepo;
    }

    @Override
    public ApplianceModelDto create(ApplianceModelDto hooverModel, Long hooverId)
            throws ModelAlreadyExistException, ApplianceNotFoundException {
        if (hooverModelRepo.getByNameContainingIgnoreCase(hooverModel.getName()) != null) {
            throw new ModelAlreadyExistException("Модель пылесоса с таким именем уже существует!");
        } else if (hooverRepo.findById(hooverId).isPresent()) {
            HooverModelEntity hooverModelEntity = toEntity((HooverModelDto) hooverModel);
            hooverModelEntity.setHoover(hooverRepo.findById(hooverId).get());
            hooverModelRepo.save(hooverModelEntity);
            return hooverModel;
        } else
            throw new ApplianceNotFoundException("Группа пылесосов с id = " + hooverId + " для добавления модели не найдена!");
    }

    @Override
    public List<ApplianceModelDto> getAll() {
        return hooverModelRepo.findAll().stream().map(HooverModelDto::toModelDto).collect(Collectors.toList());
    }

    @Override
    public ApplianceModelDto getById(Long id) throws ModelNotFoundException {
        if (hooverModelRepo.findById(id).isPresent()) {
            return toModelDto(hooverModelRepo.findById(id).get());
        } else throw new ModelNotFoundException("Модель пылесоса с id = " + id + " не найдена!");
    }

    @Override
    public ApplianceModelDto update(ApplianceModelDto hooverModel) throws ModelNotFoundException {
        if (hooverModelRepo.findById(hooverModel.getId()).isPresent()) {
            hooverModelRepo.saveAndFlush(toEntity((HooverModelDto) hooverModel));
            return hooverModel;
        } else throw new ModelNotFoundException("Модель пылесоса для изменения (обновления) не найдена!");
    }

    @Override
    public void delete(Long id) throws ModelNotFoundException {
        if (hooverModelRepo.findById(id).isPresent()) {
            hooverModelRepo.deleteById(id);
        } else throw new ModelNotFoundException("Модель пылесоса с id = " + id + " для удаления не найдена!");
    }

    @Override
    public List<ApplianceModelDto> getByName(String name) throws ModelNotFoundException {
        return hooverModelRepo.getByNameContainingIgnoreCase(name).stream()
                .map(HooverModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    @Override
    public List<ApplianceModelDto> getByColor(String color) throws ModelNotFoundException {
        return hooverModelRepo.getByColorContainingIgnoreCase(color).stream()
                .map(HooverModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    @Override
    public List<ApplianceModelDto> getByPrice(Double low, Double high) throws ModelNotFoundException {
        return hooverModelRepo.getByPriceGreaterThanAndPriceLessThan(low, high).stream()
                .map(HooverModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<ApplianceModelDto> getByParams(
            String name, Long serialNumber, String color, String size,
            Double lowPrice, Double highPrice, Integer dustContainerVolume, Integer numberOfModes, Boolean inStock
    ) {
        return hooverModelRepo.getByParams(
                name, serialNumber, color, size, lowPrice, highPrice, dustContainerVolume, numberOfModes, inStock
        ).stream().map(HooverModelDto::toModelDto).sorted().collect(Collectors.toList());
    }
}
