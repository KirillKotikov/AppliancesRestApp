package ru.kotikov.appliances.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceDto;
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
        if (hooverRepo.getByName(hoover.getName()) != null) {
            throw new ApplianceAlreadyExistException("Группа пылесосов с таким именем уже существует!");
        } else return toDto(hooverRepo.save(toEntity(hoover)));
    }

    @Override
    public List<ApplianceDto> getAll() {
        return hooverRepo.findAll().stream().map(ApplianceDto::toDto).collect(Collectors.toList());
    }

    @Override
    public ApplianceDto getById(Long id) throws ApplianceNotFoundException {
        if (hooverRepo.findById(id).isPresent()) {
            return toDto(hooverRepo.findById(id).get());
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
    public List<ApplianceDto> findByName(String name) throws ApplianceNotFoundException {
        return hooverRepo.findByName(name).stream().map(ApplianceDto::toDto).sorted().collect(Collectors.toList());
    }
}
