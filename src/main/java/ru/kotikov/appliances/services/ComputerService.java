package ru.kotikov.appliances.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceDto;
import ru.kotikov.appliances.entity.ComputerEntity;
import ru.kotikov.appliances.entity.ComputerModelEntity;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.repository.ComputerRepo;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ComputerService implements ApplianceService {

    private final ComputerRepo computerRepo;

    public ComputerService(ComputerRepo computerRepo) {
        this.computerRepo = computerRepo;
    }

    @Override
    public ApplianceDto create(ApplianceDto computer) throws ApplianceAlreadyExistException {
        if (computerRepo.getByNameContainingIgnoreCase(computer.getName()) != null) {
            throw new ApplianceAlreadyExistException("Группа компьютеров с таким именем уже существует!");
        }
        computerRepo.save(ComputerEntity.toEntity(computer));
        return computer;
    }

    @Override
    public List<Object> getAll() {
        return computerRepo.findAll().stream().peek(
                x -> x.setComputerModels(
                        x.getComputerModels().stream().filter(ComputerModelEntity::getInStock).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }

    @Override
    public Object getById(Long id) throws ApplianceNotFoundException {
        if (computerRepo.findById(id).isPresent()) {
            ComputerEntity computerEntity = computerRepo.findById(id).get();
            computerEntity.setComputerModels(computerEntity.getComputerModels().stream()
                    .filter(ComputerModelEntity::getInStock).collect(Collectors.toList()));
            return computerEntity;
        } else throw new ApplianceNotFoundException("Группа компьютеров с id = " + id + " не найдена!");
    }

    @Override
    public ApplianceDto update(ApplianceDto computer) throws ApplianceNotFoundException {
        if (computerRepo.findById(computer.getId()).isPresent()) {
            computerRepo.saveAndFlush(ComputerEntity.toEntity(computer));
            return computer;
        } else throw new ApplianceNotFoundException("Группа компьютеров для изменения (обновления) не найдена!");
    }

    @Override
    public void delete(Long id) throws ApplianceNotFoundException {
        if (computerRepo.findById(id).isPresent()) {
            computerRepo.deleteById(id);
            log.info("Удалена группа компьютеров с id = {}", id);
        } else throw new ApplianceNotFoundException("Группа компьютеров с id = " + id + " для удаления не найдена!");
    }

    @Override
    public List<Object> findByName(String name) throws ApplianceNotFoundException {
        return computerRepo.findByNameContainingIgnoreCase(name).stream().peek(x -> x.setComputerModels(x.getComputerModels()
                .stream().filter(ComputerModelEntity::getInStock).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }
}