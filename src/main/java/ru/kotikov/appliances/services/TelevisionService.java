package ru.kotikov.appliances.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceDto;
import ru.kotikov.appliances.entity.TelevisionEntity;
import ru.kotikov.appliances.entity.TelevisionModelEntity;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.repository.TelevisionRepo;

import java.util.List;
import java.util.stream.Collectors;

import static ru.kotikov.appliances.dto.ApplianceDto.toDto;
import static ru.kotikov.appliances.entity.TelevisionEntity.toEntity;

@Log4j2
@Service
public class TelevisionService implements ApplianceService {

    private final TelevisionRepo televisionRepo;

    public TelevisionService(TelevisionRepo televisionRepo) {
        this.televisionRepo = televisionRepo;
    }

    @Override
    public ApplianceDto create(ApplianceDto television) throws ApplianceAlreadyExistException {
        if (televisionRepo.getByNameContainingIgnoreCase(television.getName()) != null) {
            throw new ApplianceAlreadyExistException("Группа телевизоров с таким именем уже существует!");
        } else return toDto(televisionRepo.save(toEntity(television)));
    }

    @Override
    public List<Object> getAll() {
        return televisionRepo.findAll().stream().peek(
                x -> x.setTelevisionModels(
                        x.getTelevisionModels().stream().filter(TelevisionModelEntity::getInStock).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }

    @Override
    public Object getById(Long id) throws ApplianceNotFoundException {
        if (televisionRepo.findById(id).isPresent()) {
            TelevisionEntity television = televisionRepo.findById(id).get();
            television.setTelevisionModels(television.getTelevisionModels().stream()
                    .filter(TelevisionModelEntity::getInStock).collect(Collectors.toList()));
            return television;
        } else throw new ApplianceNotFoundException("Группа телевизоров с id = " + id + " не найдена!");
    }

    @Override
    public ApplianceDto update(ApplianceDto television) throws ApplianceNotFoundException {
        if (televisionRepo.findById(television.getId()).isPresent()) {
            televisionRepo.saveAndFlush(toEntity(television));
            return television;
        } else throw new ApplianceNotFoundException("Группа телевизоров для изменения (обновления) не найдена!");
    }

    @Override
    public void delete(Long id) throws ApplianceNotFoundException {
        if (televisionRepo.findById(id).isPresent()) {
            televisionRepo.deleteById(id);
            log.info("Удалена группа телевизоров с id = {}", id);
        }
        throw new ApplianceNotFoundException("Группа телевизоров c id = " + id + " для удаления не найдена!");
    }

    @Override
    public List<Object> findByName(String name) throws ApplianceNotFoundException {
        return televisionRepo.findByNameContainingIgnoreCase(name).stream().peek(x -> x.setTelevisionModels(x.getTelevisionModels()
                .stream().filter(TelevisionModelEntity::getInStock).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }
}