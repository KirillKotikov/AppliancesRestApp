//package ru.kotikov.appliances.dto;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import ru.kotikov.appliances.entity.ComputerEntity;
//import ru.kotikov.appliances.entity.ComputerModelEntity;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//@NoArgsConstructor
//public class ComputerDto extends ApplianceDto {
//
//    private List<ComputerModelDto> computerModels;
//
//    public static ComputerDto toDto(ComputerEntity computerEntity) {
//        var model = new ComputerDto();
//        model.setId(computerEntity.getId());
//        model.setName(computerEntity.getName());
//        model.setProducingCountry(computerEntity.getProducingCountry());
//        model.setCompanyManufacturer(computerEntity.getCompanyManufacturer());
//        model.setAvailableOnline(computerEntity.getAvailableOnline());
//        model.setInstallmentPlan(computerEntity.getInstallmentPlan());
//        model.setComputerModels(computerEntity.getComputerModels()
//                .stream().filter(ComputerModelEntity::getInStock).map(ComputerModelDto::toModelDto)
//                .sorted((Comparator.comparing(AbstractApplianceModelDto::getPrice)))
//                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
//                .collect(Collectors.toList()));
//
//        return model;
//    }
//}
