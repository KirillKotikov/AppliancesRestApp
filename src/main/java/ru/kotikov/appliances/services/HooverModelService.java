package ru.kotikov.appliances.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceModelDto;
import ru.kotikov.appliances.dto.HooverModelDto;
import ru.kotikov.appliances.entity.HooverModelEntity;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.repository.HooverModelRepo;
import ru.kotikov.appliances.repository.HooverRepo;

import java.util.ArrayList;
import java.util.List;

import static ru.kotikov.appliances.entity.HooverModelEntity.toEntity;

@Log4j2
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
    public List<Object> getAll() {
        return new ArrayList<>(hooverModelRepo.findAll());
    }

    @Override
    public Object getById(Long id) throws ModelNotFoundException {
        if (hooverModelRepo.findById(id).isPresent()) {
            return hooverModelRepo.findById(id).get();
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
            log.info("Удалена модель пылесоса с id = {}", id);
        } else throw new ModelNotFoundException("Модель пылесоса с id = " + id + " для удаления не найдена!");
    }

    @Override
    public List<Object> getByName(String name) throws ModelNotFoundException {
        return new ArrayList<>(hooverModelRepo.getByNameContainingIgnoreCase(name));
    }

    @Override
    public List<Object> getByColor(String color) throws ModelNotFoundException {
        return new ArrayList<>(hooverModelRepo.getByColorContainingIgnoreCase(color));
    }

    @Override
    public List<Object> getByPrice(Double low, Double high) throws ModelNotFoundException {
        return new ArrayList<>(hooverModelRepo.getByPriceGreaterThanAndPriceLessThan(low, high));
    }

    @Override
    public List<Object> getByParams(
            String name, Long serialNumber, String color, String size,
            Double lowPrice, Double highPrice, Object dustContainerVolume, Object numberOfModes, Boolean inStock
    ) {
        return new ArrayList<>(hooverModelRepo.getByParams(
                name, serialNumber, color, size, lowPrice, highPrice, (Integer) dustContainerVolume, (Integer) numberOfModes, inStock
        ));
    }
}