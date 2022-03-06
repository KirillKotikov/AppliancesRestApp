package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.models.*;
import ru.kotikov.appliances.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {

    @Autowired
    private ComputerRepo computerRepo;
    @Autowired
    private FridgeRepo fridgeRepo;
    @Autowired
    private HooverRepo hooverRepo;
    @Autowired
    private SmartphoneRepo smartphoneRepo;
    @Autowired
    private TelevisionRepo televisionRepo;

    public List<AbstractAppliance> getAll() {
        List<AbstractAppliance> list = new ArrayList<>();
        computerRepo.findAll().stream().map(Computer::toModel).forEach(list::add);
        fridgeRepo.findAll().stream().map(Fridge::toModel).forEach(list::add);
        hooverRepo.findAll().stream().map(Hoover::toModel).forEach(list::add);
        smartphoneRepo.findAll().stream().map(Smartphone::toModel).forEach(list::add);
        televisionRepo.findAll().stream().map(Television::toModel).forEach(list::add);
        return list.stream().sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName())).collect(Collectors.toList());
    }
}


