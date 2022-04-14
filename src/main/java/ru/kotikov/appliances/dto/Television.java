//package ru.kotikov.appliances.dto;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import ru.kotikov.appliances.entity.TelevisionEntity;
//import ru.kotikov.appliances.entity.TelevisionModelEntity;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//@NoArgsConstructor
//public class Television extends ApplianceDto {
//
//    private List<TelevisionModelDto> televisionModels;
//
//    public static Television toModelDto(TelevisionEntity televisionEntity) {
//        Television model = new Television();
//        model.setId(televisionEntity.getId());
//        model.setName(televisionEntity.getName());
//        model.setProducingCountry(televisionEntity.getProducingCountry());
//        model.setCompanyManufacturer(televisionEntity.getCompanyManufacturer());
//        model.setAvailableOnline(televisionEntity.getAvailableOnline());
//        model.setInstallmentPlan(televisionEntity.getInstallmentPlan());
//        model.setTelevisionModels(televisionEntity.getTelevisionModels()
//                .stream().filter(TelevisionModelEntity::getInStock).map(TelevisionModelDto::toModelDto)
//                .sorted((Comparator.comparing(AbstractApplianceModelDto::getPrice)))
//                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
//                .collect(Collectors.toList()));
//
//        return model;
//    }
//}
