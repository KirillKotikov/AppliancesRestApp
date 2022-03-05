package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.ApplianceEntity;
import ru.kotikov.appliances.exptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exptions.ApplianceNotFoundException;
import ru.kotikov.appliances.models.Appliance;
import ru.kotikov.appliances.repository.ApplianceRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplianceService {

    @Autowired
    private ApplianceRepo applianceRepo;

    public Appliance create(ApplianceEntity appliance) throws ApplianceAlreadyExistException {
        if (applianceRepo.findByApplianceName(appliance.getApplianceName()) != null) {
            throw new ApplianceAlreadyExistException("Прибор с таким именем уже существует!");
        }
        return Appliance.toModel(applianceRepo.save(appliance));
    }

    public List<Appliance> getAll() {
        return applianceRepo.findAll().stream().sorted((o1, o2) -> o1.getApplianceName().compareToIgnoreCase(o2.getApplianceName()))
                .map(Appliance::toModel).collect(Collectors.toList());
    }

    public Appliance getOne(Long id) throws ApplianceNotFoundException {
        ApplianceEntity appliance = applianceRepo.findById(id).get();
        if (appliance == null) {
            throw new ApplianceNotFoundException("Прибор не найден!");
        }
        return Appliance.toModel(appliance);
    }

    public ApplianceEntity update(ApplianceEntity appliance) {
      return applianceRepo.saveAndFlush(appliance);
    }

    public Long delete(Long id) {
        applianceRepo.deleteById(id);
        return id;
    }
}


