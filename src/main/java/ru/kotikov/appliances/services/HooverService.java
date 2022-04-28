package ru.kotikov.appliances.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceDto;
import ru.kotikov.appliances.entity.HooverEntity;
import ru.kotikov.appliances.entity.HooverModelEntity;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.repository.HooverRepo;

import java.util.List;
import java.util.stream.Collectors;

import static ru.kotikov.appliances.dto.ApplianceDto.toDto;
import static ru.kotikov.appliances.entity.HooverEntity.toEntity;

@Log4j2
@Service
public class HooverService implements ApplianceService {

    private final HooverRepo hooverRepo;

    public HooverService(HooverRepo hooverRepo) {
        this.hooverRepo = hooverRepo;
    }

    @Override
    public ApplianceDto create(ApplianceDto hoover) throws ApplianceAlreadyExistException {
        if (hooverRepo.getByNameContainingIgnoreCase(hoover.getName()) != null) {
            throw new ApplianceAlreadyExistException("Группа пылесосов с таким именем уже существует!");
        } else return toDto(hooverRepo.save(toEntity(hoover)));
    }

    @Override
    public List<Object> getAll() {
        return hooverRepo.findAll().stream().peek(
                x -> x.setHooverModels(
                        x.getHooverModels().stream().filter(HooverModelEntity::getInStock).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }

    @Override
    public Object getById(Long id) throws ApplianceNotFoundException {
        if (hooverRepo.findById(id).isPresent()) {
            HooverEntity hoover = hooverRepo.findById(id).get();
            hoover.setHooverModels(hoover.getHooverModels().stream()
                    .filter(HooverModelEntity::getInStock).collect(Collectors.toList()));
            return hoover;
        } else throw new ApplianceNotFoundException("Группа пылесосов с id = " + id + " для удаления не найдена!");
    }

    @Override
    public ApplianceDto update(ApplianceDto hoover) throws ApplianceNotFoundException {
        if (hooverRepo.findById(hoover.getId()).isPresent()) {
            hooverRepo.saveAndFlush(toEntity(hoover));
            return hoover;
        } else throw new ApplianceNotFoundException("Группа пылесосов не найдена!");
    }

    @Override
    public void delete(Long id) throws ApplianceNotFoundException {
        if (hooverRepo.findById(id).isPresent()) {
            hooverRepo.deleteById(id);
            log.info("Удалена группа пылесосов с id = {}", id);
        }
        throw new ApplianceNotFoundException("Группа пылесосов с id = " + id + " для удаления не найдена!");
    }

    @Override
    public List<Object> findByName(String name) throws ApplianceNotFoundException {
        return hooverRepo.findByNameContainingIgnoreCase(name).stream().peek(x -> x.setHooverModels(x.getHooverModels()
                .stream().filter(HooverModelEntity::getInStock).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }
}