package ru.kotikov.appliances.services;

import org.springframework.stereotype.Service;
import ru.kotikov.appliances.dto.ApplianceDto;
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

    public List<ApplianceDto> getAll() {
        List<ApplianceDto> list = new ArrayList<>();
        computerRepo.findAll().stream().map(ApplianceDto::toDto).forEach(list::add);
        fridgeRepo.findAll().stream().map(ApplianceDto::toDto).forEach(list::add);
        hooverRepo.findAll().stream().map(ApplianceDto::toDto).forEach(list::add);
        smartphoneRepo.findAll().stream().map(ApplianceDto::toDto).forEach(list::add);
        televisionRepo.findAll().stream().map(ApplianceDto::toDto).forEach(list::add);
        return list.stream().sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName())).collect(Collectors.toList());
    }
}


