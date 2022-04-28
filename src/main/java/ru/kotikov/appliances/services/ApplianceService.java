package ru.kotikov.appliances.services;

import ru.kotikov.appliances.dto.ApplianceDto;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;

import java.util.List;

public interface ApplianceService {

    ApplianceDto create(ApplianceDto computer) throws ApplianceAlreadyExistException;

    List<Object> getAll();

    Object getById(Long id) throws ApplianceNotFoundException;

    ApplianceDto update(ApplianceDto computer) throws ApplianceNotFoundException;

    void delete(Long id) throws ApplianceNotFoundException;

    List<Object> findByName(String name) throws ApplianceNotFoundException;
}