package ru.kotikov.appliances.services;

import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceDto;
import ru.kotikov.appliances.entity.AbstractApplianceEntity;
import ru.kotikov.appliances.entity.ComputerEntity;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.repository.ComputerRepo;

import java.util.List;
import java.util.stream.Collectors;

import static ru.kotikov.appliances.dto.ApplianceDto.toDto;

@Service
public class ComputerService {

    private final ComputerRepo computerRepo;

    public ComputerService(ComputerRepo computerRepo) {
        this.computerRepo = computerRepo;
    }

    public ApplianceDto create(ApplianceDto computer) throws ApplianceAlreadyExistException {
        if (computerRepo.findByName(computer.getName()) != null) {
            throw new ApplianceAlreadyExistException("Группа компьютеров с таким именем уже существует!");
        }
        return toDto(computerRepo.save(ComputerEntity.toEntity(computer)));
    }

    public List<ApplianceDto> getAll() {
        return computerRepo.findAll().stream().sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(ApplianceDto::toDto).collect(Collectors.toList());
    }

    public ApplianceDto searchById(Long id) throws ApplianceNotFoundException {
        if (computerRepo.findById(id).isPresent()) {
            return toDto(computerRepo.findById(id).get());
        } else throw new ApplianceNotFoundException("Группа компьютеров с id = " + id + " не найдена!");
    }

    public ApplianceDto update(ApplianceDto computer) throws ApplianceNotFoundException {
        if (computerRepo.findById(computer.getId()).isPresent()) {
            computerRepo.saveAndFlush(ComputerEntity.toEntity(computer));
            return computer;
        } else throw new ApplianceNotFoundException("Группа компьютеров для изменения (обновления) не найдена!");
    }

    public void delete(Long id) throws ApplianceNotFoundException {
        if (computerRepo.findById(id).isPresent()) {
            computerRepo.deleteById(id);
        } else throw new ApplianceNotFoundException("Группа компьютеров с id = " + id + " для удаления не найдена!");
    }

    public List<ApplianceDto> searchByName(String name) throws ApplianceNotFoundException {
        return computerRepo.findAll().stream()
                .filter(x -> x.getName().equalsIgnoreCase(name))
                .map(ApplianceDto::toDto).sorted().collect(Collectors.toList());
    }
}