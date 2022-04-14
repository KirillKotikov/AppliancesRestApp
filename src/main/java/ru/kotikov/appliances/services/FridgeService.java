package ru.kotikov.appliances.services;

import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceDto;
import ru.kotikov.appliances.entity.FridgeEntity;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.repository.FridgeRepo;

import java.util.List;
import java.util.stream.Collectors;

import static ru.kotikov.appliances.dto.ApplianceDto.toDto;

@Service
public class FridgeService {

    private final FridgeRepo fridgeRepo;

    public FridgeService(FridgeRepo fridgeRepo) {
        this.fridgeRepo = fridgeRepo;
    }

    public ApplianceDto create(ApplianceDto fridge) throws ApplianceAlreadyExistException {
        if (fridgeRepo.findByName(fridge.getName()) != null) {
            throw new ApplianceAlreadyExistException("Группа холодильника с таким именем уже существует!");
        }
        return toDto(fridgeRepo.save(FridgeEntity.toEntity(fridge)));
    }

    public List<ApplianceDto> getAll() {
        return fridgeRepo.findAll().stream().sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(ApplianceDto::toDto).collect(Collectors.toList());
    }

    public ApplianceDto searchById(Long id) throws ApplianceNotFoundException {
        if (fridgeRepo.findById(id).isPresent()) {
            return toDto(fridgeRepo.findById(id).get());
        } else throw new ApplianceNotFoundException("Группа пылесосов с id = " + id + " не найден!");
    }

    public ApplianceDto update(ApplianceDto fridge) throws ApplianceNotFoundException {
        if (fridgeRepo.findById(fridge.getId()).isPresent()) {
            fridgeRepo.saveAndFlush(FridgeEntity.toEntity(fridge));
            return fridge;
        } else throw new ApplianceNotFoundException("Группа холодильников не найдена!");
    }

    public void delete(Long id) throws ApplianceNotFoundException {
        if (fridgeRepo.findById(id).isPresent()) {
            fridgeRepo.deleteById(id);
        } else throw new ApplianceNotFoundException("Группа холодильников с id = " + id + " для удаления не найдена!");
    }

    public List<ApplianceDto> searchByName(String name) throws ApplianceNotFoundException {
        return fridgeRepo.findAll().stream()
                .filter(x -> x.getName().equalsIgnoreCase(name))
                .map(ApplianceDto::toDto).sorted().collect(Collectors.toList());
    }
}
