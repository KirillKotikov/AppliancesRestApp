//package ru.kotikov.appliances.dto;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import ru.kotikov.appliances.entity.HooverEntity;
//import ru.kotikov.appliances.entity.HooverModelEntity;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//@NoArgsConstructor
//public class Hoover extends ApplianceDto {
//
//        private List<HooverModelDto> hooverModels;
//
//        public static Hoover toModelDto(HooverEntity hooverEntity) {
//            Hoover model = new Hoover();
//            model.setId(hooverEntity.getId());
//            model.setName(hooverEntity.getName());
//            model.setProducingCountry(hooverEntity.getProducingCountry());
//            model.setCompanyManufacturer(hooverEntity.getCompanyManufacturer());
//            model.setAvailableOnline(hooverEntity.getAvailableOnline());
//            model.setInstallmentPlan(hooverEntity.getInstallmentPlan());
//            model.setHooverModels(hooverEntity.getHooverModels()
//                    .stream().filter(HooverModelEntity::getInStock).map(HooverModelDto::toModelDto)
//                    .sorted((Comparator.comparing(AbstractApplianceModelDto::getPrice)))
//                    .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
//                    .collect(Collectors.toList()));
//
//            return model;
//        }
//
//}
