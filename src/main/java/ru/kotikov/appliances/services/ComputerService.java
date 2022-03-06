package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.ComputerEntity;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.models.Computer;
import ru.kotikov.appliances.models.Television;
import ru.kotikov.appliances.repository.ComputerRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComputerService {

    @Autowired
    private ComputerRepo computerRepo;

    public Computer create(ComputerEntity computer) throws ApplianceAlreadyExistException {
        if (computerRepo.findByName(computer.getName()) != null) {
            throw new ApplianceAlreadyExistException("Компьютер с таким именем уже существует!");
        }
        return Computer.toModel(computerRepo.save(computer));
    }

    public List<Computer> getAll() {
        return computerRepo.findAll().stream().sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(Computer::toModel).collect(Collectors.toList());
    }

    public Computer getOne(Long id) throws ApplianceNotFoundException {
        ComputerEntity computerEntity = computerRepo.findById(id).get();
        if (computerEntity == null) {
            throw new ApplianceNotFoundException("Компьютер не найден!");
        }
        return Computer.toModel(computerEntity);
    }

    public ComputerEntity update(ComputerEntity computer) throws ApplianceNotFoundException {
        ComputerEntity computerEntity = computerRepo.findById(computer.getId()).get();
        if (computerEntity == null) {
            throw new ApplianceNotFoundException("Компьютер не найден!");
        }
        return computerRepo.saveAndFlush(computer);
    }

    public Long delete(Long id) throws ApplianceNotFoundException {
        ComputerEntity computerEntity = computerRepo.findById(id).get();
        if (computerEntity == null) {
            throw new ApplianceNotFoundException("Компьютер не найден!");
        }
        computerRepo.deleteById(id);
        return id;
    }

    public List<Computer> searchForName(String name) throws ApplianceNotFoundException {
        List<Computer> computers = computerRepo.findAll().stream()
                .filter(x -> x.getName().equalsIgnoreCase(name))
                .map(Computer::toModel).sorted().collect(Collectors.toList());
        if (computers.size() == 0) throw new ApplianceNotFoundException("Техника с таким именем не найдена!");
        return computers;
    }
}