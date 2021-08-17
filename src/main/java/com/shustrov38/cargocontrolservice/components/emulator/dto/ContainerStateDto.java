package com.shustrov38.cargocontrolservice.components.emulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerStateDto {

    private Instant time;
    private Map<String, Double> sensorValues;
}
