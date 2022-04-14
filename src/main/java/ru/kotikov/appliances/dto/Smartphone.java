//package ru.kotikov.appliances.dto;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import ru.kotikov.appliances.entity.SmartphoneEntity;
//import ru.kotikov.appliances.entity.SmartphoneModelEntity;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//@NoArgsConstructor
//public class Smartphone extends ApplianceDto {
//
//    private List<SmartphoneModelDto> smartphoneModels;
//
//    public static Smartphone toModelDto(SmartphoneEntity smartphoneEntity) {
//        var model = new Smartphone();
//        model.setId(smartphoneEntity.getId());
//        model.setName(smartphoneEntity.getName());
//        model.setProducingCountry(smartphoneEntity.getProducingCountry());
//        model.setCompanyManufacturer(smartphoneEntity.getCompanyManufacturer());
//        model.setAvailableOnline(smartphoneEntity.getAvailableOnline());
//        model.setInstallmentPlan(smartphoneEntity.getInstallmentPlan());
//        model.setSmartphoneModels(smartphoneEntity.getSmartphoneModels()
//                .stream().filter(SmartphoneModelEntity::getInStock).map(SmartphoneModelDto::toModelDto)
//                .sorted((Comparator.comparing(AbstractApplianceModelDto::getPrice)))
//                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
//                .collect(Collectors.toList()));
//
//        return model;
//    }
//
//}
