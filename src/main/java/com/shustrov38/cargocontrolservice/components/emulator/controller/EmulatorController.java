package com.shustrov38.cargocontrolservice.components.emulator.controller;

import com.shustrov38.cargocontrolservice.components.emulator.dto.EmulationDataDto;
import com.shustrov38.cargocontrolservice.components.emulator.dto.EmulatorConfiguration;
import com.shustrov38.cargocontrolservice.components.emulator.service.Emulator;
import com.shustrov38.cargocontrolservice.components.postgre.service.PostgreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.concurrent.Callable;

@RestController
public class EmulatorController {

    private Integer totalEmulations;

    @Autowired
    public EmulatorController(
            PostgreService postgreService) {
        totalEmulations = postgreService.getEmulationDataCount();
    }

    @PostMapping("/emulate")
    public Callable<EmulationDataDto> emulate(@Valid @RequestBody EmulatorConfiguration configuration) {
        return () -> {
            long startTime = System.nanoTime();

            var result = new Emulator(configuration, ++totalEmulations).emulate();

            long timeoutNanos = configuration.getSendTimeout().longValue() * 1_000_000_000;
            while (System.nanoTime() - startTime < timeoutNanos) {
                // work simulation :)
                Thread.sleep(1);
            }

            var response = new RestTemplate()
                    .postForObject("http://localhost:8080/sensor-request-manager", result, EmulationDataDto.class);

            return result;
        };
    }
}
