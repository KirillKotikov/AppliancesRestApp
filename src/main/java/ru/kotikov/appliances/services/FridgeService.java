package ru.kotikov.appliances.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceDto;
import ru.kotikov.appliances.entity.FridgeEntity;
import ru.kotikov.appliances.entity.FridgeModelEntity;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.repository.FridgeRepo;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class FridgeService implements ApplianceService {

    private final FridgeRepo fridgeRepo;

    public FridgeService(FridgeRepo fridgeRepo) {
        this.fridgeRepo = fridgeRepo;
    }

    @Override
    public ApplianceDto create(ApplianceDto fridge) throws ApplianceAlreadyExistException {
        if (fridgeRepo.getByNameContainingIgnoreCase(fridge.getName()) != null) {
            throw new ApplianceAlreadyExistException("Группа холодильника с таким именем уже существует!");
        }
        fridgeRepo.save(FridgeEntity.toEntity(fridge));
        return fridge;
    }

    @Override
    public List<Object> getAll() {
        return fridgeRepo.findAll().stream().peek(
                x -> x.setFridgeModels(
                        x.getFridgeModels().stream().filter(FridgeModelEntity::getInStock).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }

    @Override
    public Object getById(Long id) throws ApplianceNotFoundException {
        if (fridgeRepo.findById(id).isPresent()) {
            FridgeEntity fridge = fridgeRepo.findById(id).get();
            fridge.setFridgeModels(fridge.getFridgeModels().stream()
                    .filter(FridgeModelEntity::getInStock).collect(Collectors.toList()));
            return fridge;
        } else throw new ApplianceNotFoundException("Группа пылесосов с id = " + id + " не найден!");
    }

    @Override
    public ApplianceDto update(ApplianceDto fridge) throws ApplianceNotFoundException {
        if (fridgeRepo.findById(fridge.getId()).isPresent()) {
            fridgeRepo.saveAndFlush(FridgeEntity.toEntity(fridge));
            return fridge;
        } else throw new ApplianceNotFoundException("Группа холодильников не найдена!");
    }

    @Override
    public void delete(Long id) throws ApplianceNotFoundException {
        if (fridgeRepo.findById(id).isPresent()) {
            fridgeRepo.deleteById(id);
            log.info("Удалена группа холодильников с id = {}", id);
        } else throw new ApplianceNotFoundException("Группа холодильников с id = " + id + " для удаления не найдена!");
    }

    @Override
    public List<Object> findByName(String name) throws ApplianceNotFoundException {
        return fridgeRepo.findByNameContainingIgnoreCase(name).stream().peek(x -> x.setFridgeModels(x.getFridgeModels()
                .stream().filter(FridgeModelEntity::getInStock).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }
}