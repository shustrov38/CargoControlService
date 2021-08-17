package com.shustrov38.cargocontrolservice.components.postgre.mapper;

public class Mappers {

    private Mappers() {
    }

    public static final EmulationDataMapper EMULATION_DATA_MAPPER =
            org.mapstruct.factory.Mappers.getMapper(EmulationDataMapper.class);
}
