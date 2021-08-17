package com.shustrov38.cargocontrolservice.components.emulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmulationDataDto {

    private List<ContainerDto> containers;
}
