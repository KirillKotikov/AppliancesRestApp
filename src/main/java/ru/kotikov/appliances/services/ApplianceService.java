package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.ApplianceEntity;
import ru.kotikov.appliances.entities.TelevisionEntity;
import ru.kotikov.appliances.exptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exptions.ApplianceNotFoundException;
import ru.kotikov.appliances.exptions.TelevisionNotFoundException;
import ru.kotikov.appliances.models.Appliance;
import ru.kotikov.appliances.models.Television;
import ru.kotikov.appliances.repository.ApplianceRepo;
import ru.kotikov.appliances.repository.TelevisionRepo;

@Service
public class ApplianceService {

    @Autowired
    private ApplianceRepo applianceRepo;

    public ApplianceEntity createAppliance (ApplianceEntity appliance) throws ApplianceAlreadyExistException {
        if (applianceRepo.findByApplianceName(appliance.getApplianceName()) != null) {
            throw new ApplianceAlreadyExistException("Прибор с таким именем уже существует!");
        }
        return applianceRepo.save(appliance);
    }

    public Appliance getOne (Long id) throws ApplianceNotFoundException {
        ApplianceEntity appliance = applianceRepo.findById(id).get();
        if (appliance == null) {
            throw new ApplianceNotFoundException("Прибор не найден!");
        }
        return Appliance.toModel(appliance);
    }

    public Long delete (Long id) {
        applianceRepo.deleteById(id);
        return id;
    }
}


