package ru.kotikov.appliances.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kotikov.appliances.entity.ApplianceEntity;

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
    @JsonIgnore
    private List<Object> models;

    public static ApplianceDto toDto(ApplianceEntity appliance) {
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