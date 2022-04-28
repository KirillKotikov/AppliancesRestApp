package ru.kotikov.appliances.services;

import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceDto;
import ru.kotikov.appliances.entity.*;
import ru.kotikov.appliances.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {

    private final ComputerRepo computerRepo;
    private final FridgeRepo fridgeRepo;
    private final HooverRepo hooverRepo;
    private final SmartphoneRepo smartphoneRepo;
    private final TelevisionRepo televisionRepo;

    public MainService(ComputerRepo computerRepo, FridgeRepo fridgeRepo, HooverRepo hooverRepo,
                       SmartphoneRepo smartphoneRepo, TelevisionRepo televisionRepo) {
        this.computerRepo = computerRepo;
        this.fridgeRepo = fridgeRepo;
        this.hooverRepo = hooverRepo;
        this.smartphoneRepo = smartphoneRepo;
        this.televisionRepo = televisionRepo;
    }

    public List<Object> getAll() {
        List<Object> list = new ArrayList<>();
        list.addAll(computerRepo.findAll().stream().peek(
                x -> x.setComputerModels(
                        x.getComputerModels().stream().filter(ComputerModelEntity::getInStock).collect(Collectors.toList()))
        ).collect(Collectors.toList()));
        list.addAll(fridgeRepo.findAll().stream().peek(
                x -> x.setFridgeModels(
                        x.getFridgeModels().stream().filter(FridgeModelEntity::getInStock).collect(Collectors.toList()))
        ).collect(Collectors.toList()));
        list.addAll(hooverRepo.findAll().stream().peek(
                x -> x.setHooverModels(
                        x.getHooverModels().stream().filter(HooverModelEntity::getInStock).collect(Collectors.toList()))
        ).collect(Collectors.toList()));
        list.addAll(smartphoneRepo.findAll().stream().peek(
                x -> x.setSmartphoneModels(
                        x.getSmartphoneModels().stream().filter(SmartphoneModelEntity::getInStock).collect(Collectors.toList()))
        ).collect(Collectors.toList()));
        list.addAll(televisionRepo.findAll().stream().peek(
                x -> x.setTelevisionModels(
                        x.getTelevisionModels().stream().filter(TelevisionModelEntity::getInStock).collect(Collectors.toList()))
        ).collect(Collectors.toList()));
        return list;
    }
}