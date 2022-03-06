package ru.kotikov.appliances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotikov.appliances.entities.HooverEntity;
import ru.kotikov.appliances.exceptions.ApplianceAlreadyExistException;
import ru.kotikov.appliances.exceptions.ApplianceNotFoundException;
import ru.kotikov.appliances.models.Hoover;
import ru.kotikov.appliances.repository.HooverRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HooverService {

        @Autowired
        private HooverRepo hooverRepo;

        public Hoover create(HooverEntity hoover) throws ApplianceAlreadyExistException {
            if (hooverRepo.findByName(hoover.getName()) != null) {
                throw new ApplianceAlreadyExistException("Пылесос с таким именем уже существует!");
            }
            return Hoover.toModel(hooverRepo.save(hoover));
        }

        public List<Hoover> getAll() {
            return hooverRepo.findAll().stream().sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                    .map(Hoover::toModel).collect(Collectors.toList());
        }

        public Hoover getOne(Long id) throws ApplianceNotFoundException {
            HooverEntity hoover = hooverRepo.findById(id).get();
            if (hoover == null) {
                throw new ApplianceNotFoundException("Пылесос не найден!");
            }
            return Hoover.toModel(hoover);
        }

        public HooverEntity update(HooverEntity hoover) throws ApplianceNotFoundException {
            HooverEntity hooverEntity = hooverRepo.findById(hoover.getId()).get();
            if (hooverEntity == null) {
                throw new ApplianceNotFoundException("Пылесос не найден!");
            }
            return hooverRepo.saveAndFlush(hoover);
        }

        public String delete(Long id) throws ApplianceNotFoundException {
            HooverEntity hoover = hooverRepo.findById(id).get();
            if (hoover == null) {
                throw new ApplianceNotFoundException("Пылесос не найден!");
            }
            hooverRepo.deleteById(id);
            return "Пылесос с id = " + id + " успешно удалён!";
        }

        public List<Hoover> searchForName(String name) {
            return hooverRepo.findAll().stream().filter(x -> x.getName().equalsIgnoreCase(name))
                    .map(Hoover::toModel).sorted().collect(Collectors.toList());
        }
}
