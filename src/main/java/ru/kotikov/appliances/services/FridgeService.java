package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.FridgeEntity;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.models.Fridge;
import ru.kotikov.appliances.repository.FridgeRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FridgeService {

    @Autowired
    private FridgeRepo fridgeRepo;

    public Fridge create(FridgeEntity fridge) throws ApplianceAlreadyExistException {
        if (fridgeRepo.findByName(fridge.getName()) != null) {
            throw new ApplianceAlreadyExistException("Холодильник с таким именем уже существует!");
        }
        return Fridge.toModel(fridgeRepo.save(fridge));
    }

    public List<Fridge> getAll() {
        return fridgeRepo.findAll().stream().sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .map(Fridge::toModel).collect(Collectors.toList());
    }

    public Fridge getOne(Long id) throws ApplianceNotFoundException {
        FridgeEntity fridge = fridgeRepo.findById(id).get();
        if (fridge == null) {
            throw new ApplianceNotFoundException("Пылесос не найден!");
        }
        return Fridge.toModel(fridge);
    }

    public FridgeEntity update(FridgeEntity fridge) throws ApplianceNotFoundException {
        FridgeEntity fridgeEntity = fridgeRepo.findById(fridge.getId()).get();
        if (fridgeEntity == null) {
            throw new ApplianceNotFoundException("Холодильник не найден!");
        }
        return fridgeRepo.saveAndFlush(fridge);
    }

    public String delete(Long id) throws ApplianceNotFoundException {
        FridgeEntity fridge = fridgeRepo.findById(id).get();
        if (fridge == null) {
            throw new ApplianceNotFoundException("Холодильник не найден!");
        }
        fridgeRepo.deleteById(id);
        return "Холодильник с id = " + id + " успешно удалён!";
    }

    public List<Fridge> serchForName(String name) {
        return fridgeRepo.findAll().stream().filter(x -> x.getName().equalsIgnoreCase(name))
                .map(Fridge::toModel).sorted().collect(Collectors.toList());
    }
}
