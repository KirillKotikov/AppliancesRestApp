package ru.kotikov.appliances.services;

import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceDto;
import ru.kotikov.appliances.entity.SmartphoneEntity;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.repository.SmartphoneRepo;

import java.util.List;
import java.util.stream.Collectors;

import static ru.kotikov.appliances.dto.ApplianceDto.toDto;

@Service
public class SmartphoneService {

    private final SmartphoneRepo smartphoneRepo;

    public SmartphoneService(SmartphoneRepo smartphoneRepo) {
        this.smartphoneRepo = smartphoneRepo;
    }

    public ApplianceDto create(ApplianceDto smartphone) throws ApplianceAlreadyExistException {
        if (smartphoneRepo.findByName(smartphone.getName()) != null) {
            throw new ApplianceAlreadyExistException("Группа смартфонов с таким именем уже существует!");
        } else {
            return toDto(smartphoneRepo.save(SmartphoneEntity.toEntity(smartphone)));
        }
    }

    public List<ApplianceDto> getAll() {
        return smartphoneRepo.findAll().stream().sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(ApplianceDto::toDto).collect(Collectors.toList());
    }

    public ApplianceDto searchById(Long id) throws ApplianceNotFoundException {
        if (smartphoneRepo.findById(id).isPresent()) {
            return toDto(smartphoneRepo.findById(id).get());
        } else throw new ApplianceNotFoundException("Группа смартфонов с id = " + id + " не найдена!");
    }

    public ApplianceDto update(ApplianceDto smartphone) throws ApplianceNotFoundException {
        if (smartphoneRepo.findById(smartphone.getId()).isPresent()) {
            smartphoneRepo.saveAndFlush(SmartphoneEntity.toEntity(smartphone));
            return smartphone;
        } else throw new ApplianceNotFoundException("Группа смартфонов для изменения (обновления) не найдена!");
    }

    public void delete(Long id) throws ApplianceNotFoundException {
        if (smartphoneRepo.findById(id).isPresent()) {
            smartphoneRepo.deleteById(id);
        } else throw new ApplianceNotFoundException("Группа смартфонов с id = " + id + " для удаления не найдена!");
    }

    public List<ApplianceDto> searchByName(String name) throws ApplianceNotFoundException {
        return smartphoneRepo.findAll().stream()
                .filter(x -> x.getName().equalsIgnoreCase(name))
                .map(ApplianceDto::toDto).sorted().collect(Collectors.toList());
    }
}
