//package ru.kotikov.appliances.dto;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import ru.kotikov.appliances.entity.FridgeEntity;
//import ru.kotikov.appliances.entity.FridgeModelEntity;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//@NoArgsConstructor
//public class Fridge extends ApplianceDto {
//
//    private List<FridgeModelDto> frudgeModels;
//
//    public static Fridge toModelDto(FridgeEntity fridgeEntity) {
//        var model = new Fridge();
//        model.setId(fridgeEntity.getId());
//        model.setName(fridgeEntity.getName());
//        model.setProducingCountry(fridgeEntity.getProducingCountry());
//        model.setCompanyManufacturer(fridgeEntity.getCompanyManufacturer());
//        model.setAvailableOnline(fridgeEntity.getAvailableOnline());
//        model.setInstallmentPlan(fridgeEntity.getInstallmentPlan());
//        model.setFrudgeModels(fridgeEntity.getFridgeModels()
//                .stream().filter(FridgeModelEntity::getInStock).map(FridgeModelDto::toModelDto)
//                .sorted((Comparator.comparing(AbstractApplianceModelDto::getPrice)))
//                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
//                .collect(Collectors.toList()));
//
//        return model;
//    }
//
//}
