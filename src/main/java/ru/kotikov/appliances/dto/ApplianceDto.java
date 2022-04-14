package ru.kotikov.appliances.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entity.AbstractApplianceEntity;

import java.util.List;

@Data
@NoArgsConstructor
public class ApplianceDto {
    private Long id;
    private String name;
    private String producingCountry;
    private String companyManufacturer;
    private Boolean availableOnline;
    private Boolean installmentPlan;
    private List<Object> models;

    public static ApplianceDto toDto(AbstractApplianceEntity appliance) {
        ApplianceDto applianceDto = new ApplianceDto();

        applianceDto.setId(appliance.getId());
        applianceDto.setName(appliance.getName());
        applianceDto.setProducingCountry(appliance.getProducingCountry());
        applianceDto.setCompanyManufacturer(appliance.getCompanyManufacturer());
        applianceDto.setAvailableOnline(appliance.getAvailableOnline());
        applianceDto.setInstallmentPlan(appliance.getInstallmentPlan());
        applianceDto.setModels(appliance.getModels());

        return applianceDto;
    }
}
