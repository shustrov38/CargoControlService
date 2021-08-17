package com.shustrov38.cargocontrolservice.components.postgre.mapper;

import com.shustrov38.cargocontrolservice.components.emulator.dto.EmulationDataDto;
import com.shustrov38.cargocontrolservice.components.postgre.models.EmulationData;
import org.mapstruct.*;

@Mapper
@MapperConfig(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG
)
public interface EmulationDataMapper {

    EmulationDataDto toDto(EmulationData emulationData);

    @Mapping(target = "id", ignore = true)
    EmulationData toModel(EmulationDataDto emulationDataDto);
}
