package com.shustrov38.cargocontrolservice.components.sensorrequestmanager.controller;

import com.shustrov38.cargocontrolservice.components.emulator.dto.EmulationDataDto;
import com.shustrov38.cargocontrolservice.components.kafka.service.KafkaService;
import com.shustrov38.cargocontrolservice.components.postgre.service.PostgreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensorRequestManagerController {

    private KafkaService kafkaService;

    @Autowired
    public SensorRequestManagerController(
            KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @PostMapping("/sensor-request-manager")
    public EmulationDataDto getEmulationDataAndStoreInKafka(@RequestBody EmulationDataDto emulationDataDto) {
        kafkaService.sendEmulationData(emulationDataDto);
        return emulationDataDto;
    }
}