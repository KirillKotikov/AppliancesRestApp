package ru.kotikov.appliances.services;

import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceDto;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.repository.TelevisionRepo;

import java.util.List;
import java.util.stream.Collectors;

import static ru.kotikov.appliances.dto.ApplianceDto.toDto;
import static ru.kotikov.appliances.entity.TelevisionEntity.toEntity;

@Service
public class TelevisionService implements ApplianceService {

    private final TelevisionRepo televisionRepo;

    public TelevisionService(TelevisionRepo televisionRepo) {
        this.televisionRepo = televisionRepo;
    }

    @Override
    public ApplianceDto create(ApplianceDto television) throws ApplianceAlreadyExistException {
        if (televisionRepo.getByName(television.getName()) != null) {
            throw new ApplianceAlreadyExistException("Группа телевизоров с таким именем уже существует!");
        } else return toDto(televisionRepo.save(toEntity(television)));
    }

    @Override
    public List<ApplianceDto> getAll() {
        return televisionRepo.findAll().stream().map(ApplianceDto::toDto).collect(Collectors.toList());
    }

    @Override
    public ApplianceDto getById(Long id) throws ApplianceNotFoundException {
        if (televisionRepo.findById(id).isPresent()) {
            return toDto(televisionRepo.findById(id).get());
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
        }
        throw new ApplianceNotFoundException("Группа телевизоров c id = " + id + " для удаления не найдена!");
    }

    @Override
    public List<ApplianceDto> findByName(String name) throws ApplianceNotFoundException {
        return televisionRepo.findByName(name).stream().map(ApplianceDto::toDto).sorted().collect(Collectors.toList());
    }
}
