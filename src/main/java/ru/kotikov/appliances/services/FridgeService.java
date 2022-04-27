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
public class FridgeService implements ApplianceService {

    private final FridgeRepo fridgeRepo;

    public FridgeService(FridgeRepo fridgeRepo) {
        this.fridgeRepo = fridgeRepo;
    }

    @Override
    public ApplianceDto create(ApplianceDto fridge) throws ApplianceAlreadyExistException {
        if (fridgeRepo.getByName(fridge.getName()) != null) {
            throw new ApplianceAlreadyExistException("Группа холодильника с таким именем уже существует!");
        }
        return toDto(fridgeRepo.save(FridgeEntity.toEntity(fridge)));
    }

    @Override
    public List<ApplianceDto> getAll() {
        return fridgeRepo.findAll().stream()
                .map(ApplianceDto::toDto).collect(Collectors.toList());
    }

    @Override
    public ApplianceDto getById(Long id) throws ApplianceNotFoundException {
        if (fridgeRepo.findById(id).isPresent()) {
            return toDto(fridgeRepo.findById(id).get());
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
        } else throw new ApplianceNotFoundException("Группа холодильников с id = " + id + " для удаления не найдена!");
    }

    @Override
    public List<ApplianceDto> findByName(String name) throws ApplianceNotFoundException {
        return fridgeRepo.findByName(name).stream().map(ApplianceDto::toDto).sorted().collect(Collectors.toList());
    }
}
