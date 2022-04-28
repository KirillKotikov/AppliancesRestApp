package ru.kotikov.appliances.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceDto;
import ru.kotikov.appliances.entity.SmartphoneEntity;
import ru.kotikov.appliances.entity.SmartphoneModelEntity;
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
        if (smartphoneRepo.getByNameContainingIgnoreCase(smartphone.getName()) != null) {
            throw new ApplianceAlreadyExistException("Группа смартфонов с таким именем уже существует!");
        } else {
            return toDto(smartphoneRepo.save(SmartphoneEntity.toEntity(smartphone)));
        }
    }

    @Override
    public List<Object> getAll() {
        return smartphoneRepo.findAll().stream().peek(
                x -> x.setSmartphoneModels(
                        x.getSmartphoneModels().stream().filter(SmartphoneModelEntity::getInStock).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }

    @Override
    public Object getById(Long id) throws ApplianceNotFoundException {
        if (smartphoneRepo.findById(id).isPresent()) {
            SmartphoneEntity smartphone = smartphoneRepo.findById(id).get();
            smartphone.setSmartphoneModels(smartphone.getSmartphoneModels().stream()
                    .filter(SmartphoneModelEntity::getInStock).collect(Collectors.toList()));
            return smartphone;
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
    public List<Object> findByName(String name) throws ApplianceNotFoundException {
        return smartphoneRepo.findByNameContainingIgnoreCase(name).stream().peek(x -> x.setSmartphoneModels(x.getSmartphoneModels()
                .stream().filter(SmartphoneModelEntity::getInStock).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }
}