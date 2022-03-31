package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.TelevisionEntity;
import ru.kotikov.appliances.entities.TelevisionModelEntity;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;
import ru.kotikov.appliances.models.ComputerModel;
import ru.kotikov.appliances.models.TelevisionModel;
import ru.kotikov.appliances.repository.TelevisionModelRepo;
import ru.kotikov.appliances.repository.TelevisionRepo;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TelevisionModelService {

    @Autowired
    private TelevisionModelRepo televisionModelRepo;
    @Autowired
    private TelevisionRepo televisionRepo;

    public TelevisionModel create(TelevisionModelEntity televisionModel, Long televisionId) throws ModelAlreadyExistException {
        if (televisionModelRepo.findByName(televisionModel.getName()) != null) {
            throw new ModelAlreadyExistException("Модель телевизора с таким именем уже существует!");
        }
        TelevisionEntity television = televisionRepo.findById(televisionId).get();
        televisionModel.setTelevision(television);
        return TelevisionModel.toModel(televisionModelRepo.save(televisionModel));
    }

    public List<TelevisionModel> getAll() {
        return televisionModelRepo.findAll().stream()
                .sorted((Comparator.comparing(TelevisionModelEntity::getPrice)))
                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(TelevisionModel::toModel).collect(Collectors.toList());
    }

    public TelevisionModel getOne(Long id) throws ModelNotFoundException {
        TelevisionModelEntity television = televisionModelRepo.findById(id).get();
        if (television == null) {
            throw new ModelNotFoundException("Модель телевизора не найдена!");
        }
        return TelevisionModel.toModel(television);
    }

    public TelevisionModelEntity update(TelevisionModelEntity televisionModel) throws ModelNotFoundException {
        TelevisionModelEntity television = televisionModelRepo.findById(televisionModel.getId()).get();
        if (television == null) {
            throw new ModelNotFoundException("Модель телевизора не найдена!");
        }
        return televisionModelRepo.saveAndFlush(televisionModel);
    }

    public Long delete(Long id) throws ModelNotFoundException {
        TelevisionModelEntity television = televisionModelRepo.findById(id).get();
        if (television == null) {
            throw new ModelNotFoundException("Модель телевизора не найдена!");
        }
        televisionModelRepo.deleteById(id);
        return id;
    }

    public List<TelevisionModel> searchByName(String name) throws ModelNotFoundException {
        List<TelevisionModel> televisionModels = televisionModelRepo.findAll().stream()
                .filter(x -> x.getName().equalsIgnoreCase(name))
                .map(TelevisionModel::toModel).sorted().collect(Collectors.toList());
        if (televisionModels.size() == 0) throw new ModelNotFoundException("Модель с таким именем не найдена!");
        return televisionModels;
    }

    public List<TelevisionModel> searchByColor(String color) throws ModelNotFoundException {
        List<TelevisionModel> televisionModels = televisionModelRepo.findAll().stream()
                .filter(x -> x.getColor().equalsIgnoreCase(color))
                .map(TelevisionModel::toModel).sorted().collect(Collectors.toList());
        if (televisionModels.size() == 0) throw new ModelNotFoundException("Модель с таким цветом не найдена!");
        return televisionModels;
    }

    public List<TelevisionModel> searchByPrice(Double low, Double high) throws ModelNotFoundException {
        List<TelevisionModel> televisionModels = televisionModelRepo.findAll().stream()
                .map(TelevisionModel::toModel).sorted()
                .filter(x -> (x.getPrice() > low) && (high > x.getPrice())).collect(Collectors.toList());
        if (televisionModels.size() == 0) throw new ModelNotFoundException("Модель с такой ценой не найдена!");
        return televisionModels;
    }

    public List<TelevisionModel> searchWithFilters(
            String name, Long serialNumber, String color, String size,
            Double lowPrice, Double highPrice, String category, String technology, Boolean inStock
    ) {
        return televisionModelRepo.findAll().stream()
                .map(TelevisionModel::toModel).sorted()
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
                    if (!category.trim().isEmpty()) return x.getCategory().equalsIgnoreCase(category);
                    else return true;
                })
                .filter(x -> {
                    if (!technology.trim().isEmpty()) return x.getTechnology().equalsIgnoreCase(technology);
                    else return true;
                })
                .filter(x -> {
                    if (inStock) return x.getInStock().equals(true);
                    else return true;
                })
                .collect(Collectors.toList());
    }
}
