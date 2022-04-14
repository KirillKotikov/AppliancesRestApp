package ru.kotikov.appliances.services;

import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.HooverModelDto;
import ru.kotikov.appliances.entity.HooverModelEntity;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.repository.HooverModelRepo;
import ru.kotikov.appliances.repository.HooverRepo;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.kotikov.appliances.dto.HooverModelDto.toModelDto;
import static ru.kotikov.appliances.entity.HooverModelEntity.toEntity;

@Service
public class HooverModelService {
    private final HooverModelRepo hooverModelRepo;
    private final HooverRepo hooverRepo;

    public HooverModelService(HooverModelRepo hooverModelRepo, HooverRepo hooverRepo) {
        this.hooverModelRepo = hooverModelRepo;
        this.hooverRepo = hooverRepo;
    }

    public HooverModelDto create(HooverModelDto hooverModel, Long hooverId)
            throws ModelAlreadyExistException, ApplianceNotFoundException {
        if (hooverModelRepo.findByName(hooverModel.getName()) != null) {
            throw new ModelAlreadyExistException("Модель пылесоса с таким именем уже существует!");
        } else if (hooverRepo.findById(hooverId).isPresent()) {
            HooverModelEntity hooverModelEntity = toEntity(hooverModel);
            hooverModelEntity.setHoover(hooverRepo.findById(hooverId).get());
            hooverModelRepo.save(hooverModelEntity);
            return hooverModel;
        } else
            throw new ApplianceNotFoundException("Группа пылесосов с id = " + hooverId + " для добавления модели не найдена!");
    }

    public List<HooverModelDto> getAll() {
        return hooverModelRepo.findAll().stream()
                .sorted((Comparator.comparing(HooverModelEntity::getPrice)))
                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(HooverModelDto::toModelDto).collect(Collectors.toList());
    }

    public HooverModelDto searchById(Long id) throws ModelNotFoundException {
        if (hooverModelRepo.findById(id).isPresent()) {
            return toModelDto(hooverModelRepo.findById(id).get());
        } else throw new ModelNotFoundException("Модель пылесоса с id = " + id + " не найдена!");
    }

    public HooverModelDto update(HooverModelDto hooverModel) throws ModelNotFoundException {
        if (hooverModelRepo.findById(hooverModel.getId()).isPresent()) {
            hooverModelRepo.saveAndFlush(toEntity(hooverModel));
            return hooverModel;
        } else throw new ModelNotFoundException("Модель пылесоса для изменения (обновления) не найдена!");
    }

    public void delete(Long id) throws ModelNotFoundException {
        if (hooverModelRepo.findById(id).isPresent()) {
            hooverModelRepo.deleteById(id);
        } else throw new ModelNotFoundException("Модель пылесоса с id = " + id + " для удаления не найдена!");
    }

    public List<HooverModelDto> searchByName(String name) throws ModelNotFoundException {
        return hooverModelRepo.findAll().stream()
                .filter(x -> x.getName().equalsIgnoreCase(name))
                .map(HooverModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<HooverModelDto> searchByColor(String color) throws ModelNotFoundException {
        return hooverModelRepo.findAll().stream()
                .filter(x -> x.getColor().equalsIgnoreCase(color))
                .map(HooverModelDto::toModelDto).sorted().collect(Collectors.toList());
    }

    public List<HooverModelDto> searchByPrice(Double low, Double high) throws ModelNotFoundException {
        return hooverModelRepo.findAll().stream()
                .map(HooverModelDto::toModelDto).sorted()
                .filter(x -> (x.getPrice() > low) && (high > x.getPrice())).collect(Collectors.toList());
    }

    public List<HooverModelDto> searchWithFilters(
            String name, Long serialNumber, String color, String size,
            Double lowPrice, Double highPrice, Integer dustContainerVolume, Integer numberOfModes, Boolean inStock
    ) {
        return hooverModelRepo.findAll().stream()
                .map(HooverModelDto::toModelDto).sorted()
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
                    if (!(dustContainerVolume == 0)) return x.getDustContainerVolume().equals(dustContainerVolume);
                    else return true;
                })
                .filter(x -> {
                    if (!(numberOfModes == 0)) return x.getNumberOfModes().equals(numberOfModes);
                    else return true;
                })
                .filter(x -> {
                    if (inStock) return x.getInStock().equals(true);
                    else return true;
                })
                .collect(Collectors.toList());
    }
}
