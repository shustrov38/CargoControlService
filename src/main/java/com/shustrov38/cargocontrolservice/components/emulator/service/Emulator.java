package com.shustrov38.cargocontrolservice.components.emulator.service;

import com.shustrov38.cargocontrolservice.components.emulator.dto.ContainerDto;
import com.shustrov38.cargocontrolservice.components.emulator.dto.ContainerStateDto;
import com.shustrov38.cargocontrolservice.components.emulator.dto.EmulationDataDto;
import com.shustrov38.cargocontrolservice.components.emulator.dto.EmulatorConfiguration;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Emulator {

    private EmulatorConfiguration configuration;
    private Integer emulationNumber;

    public Emulator(EmulatorConfiguration configuration, Integer emulationNumber) {
        this.configuration = configuration;
        this.emulationNumber = emulationNumber;
    }

    private List<Instant> emulateTimeList() {
        List<Instant> result = new ArrayList<>();
        Instant T0 = configuration.getStartTime();

        for (int timeStep = 0; timeStep < configuration.getEmulationStepsCount(); ++timeStep) {
            // formula: T0 + timeStep * d + rand(0, t)
            result.add(T0 = T0.plusSeconds(
                    (long) (timeStep * configuration.getDelta()
                            + ThreadLocalRandom.current().nextDouble(0, configuration.getTimeout()))
            ));
        }

        return result;
    }

    private Map<String, Double> emulateSensorValues() {
        Map<String, Double> result = new HashMap<>();

        for (var sensor : configuration.getSensors()) {
            // formula: mP + rand(-dP, dP)
            result.put(
                    sensor.getParName(),
                    sensor.getParValue() + ThreadLocalRandom.current().nextDouble(-sensor.getDelta(), sensor.getDelta())
            );
        }

        return result;
    }

    private ContainerDto emulateOneContainer(Integer id) {
        var containerStates = emulateTimeList().stream()
                .map(time -> new ContainerStateDto(time, emulateSensorValues())).toList();

        return new ContainerDto("Emulation_" + emulationNumber + "#Container_" + id, containerStates);
    }

    public EmulationDataDto emulate() {
        List<ContainerDto> containers = new ArrayList<>();

        for (int i = 1; i <= configuration.getContainersCount(); ++i) {
            containers.add(emulateOneContainer(i));
        }

        return new EmulationDataDto(containers);
    }
}
