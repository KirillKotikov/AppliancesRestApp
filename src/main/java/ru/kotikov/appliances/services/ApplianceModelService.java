package ru.kotikov.appliances.services;

import ru.kotikov.appliances.dto.ApplianceModelDto;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.exceptions.ModelAlreadyExistException;
import ru.kotikov.appliances.exceptions.ModelNotFoundException;

import java.util.List;

interface ApplianceModelService {

    ApplianceModelDto create(ApplianceModelDto applianceModelDto, Long applianceModelId)
            throws ModelAlreadyExistException, ApplianceNotFoundException;

    List<ApplianceModelDto> getAll();

    ApplianceModelDto getById(Long id) throws ModelNotFoundException;

    ApplianceModelDto update(ApplianceModelDto applianceModelDto) throws ModelNotFoundException;

    void delete(Long id) throws ModelNotFoundException;

    List<ApplianceModelDto> getByName(String name) throws ModelNotFoundException;

    List<ApplianceModelDto> getByColor(String color) throws ModelNotFoundException;

    List<ApplianceModelDto> getByPrice(Double low, Double high) throws ModelNotFoundException;
}
