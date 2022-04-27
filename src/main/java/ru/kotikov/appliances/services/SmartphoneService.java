package ru.kotikov.appliances.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceDto;
import ru.kotikov.appliances.entity.SmartphoneEntity;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.repository.SmartphoneRepo;

import java.util.List;
import java.util.stream.Collectors;

import static ru.kotikov.appliances.dto.ApplianceDto.toDto;

@Log4j2
@Service
public class SmartphoneService implements ApplianceService {

    private final SmartphoneRepo smartphoneRepo;

    public SmartphoneService(SmartphoneRepo smartphoneRepo) {
        this.smartphoneRepo = smartphoneRepo;
    }

    @Override
    public ApplianceDto create(ApplianceDto smartphone) throws ApplianceAlreadyExistException {
        if (smartphoneRepo.getByName(smartphone.getName()) != null) {
            throw new ApplianceAlreadyExistException("Группа смартфонов с таким именем уже существует!");
        } else {
            return toDto(smartphoneRepo.save(SmartphoneEntity.toEntity(smartphone)));
        }
    }

    @Override
    public List<ApplianceDto> getAll() {
        return smartphoneRepo.findAll().stream().sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(ApplianceDto::toDto).collect(Collectors.toList());
    }

    @Override
    public ApplianceDto getById(Long id) throws ApplianceNotFoundException {
        if (smartphoneRepo.findById(id).isPresent()) {
            return toDto(smartphoneRepo.findById(id).get());
        } else throw new ApplianceNotFoundException("Группа смартфонов с id = " + id + " не найдена!");
    }

    @Override
    public ApplianceDto update(ApplianceDto smartphone) throws ApplianceNotFoundException {
        if (smartphoneRepo.findById(smartphone.getId()).isPresent()) {
            smartphoneRepo.saveAndFlush(SmartphoneEntity.toEntity(smartphone));
            return smartphone;
        } else throw new ApplianceNotFoundException("Группа смартфонов для изменения (обновления) не найдена!");
    }

    @Override
    public void delete(Long id) throws ApplianceNotFoundException {
        if (smartphoneRepo.findById(id).isPresent()) {
            smartphoneRepo.deleteById(id);
            log.info("Удалена группа смартфонов с id = {}", id);
        } else throw new ApplianceNotFoundException("Группа смартфонов с id = " + id + " для удаления не найдена!");
    }

    @Override
    public List<ApplianceDto> findByName(String name) throws ApplianceNotFoundException {
        return smartphoneRepo.findByName(name).stream().map(ApplianceDto::toDto).sorted().collect(Collectors.toList());
    }
}
