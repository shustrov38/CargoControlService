package com.shustrov38.cargocontrolservice.components.emulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerDto {

    private String name;
    private List<ContainerStateDto> state;
}
