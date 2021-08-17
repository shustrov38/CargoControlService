package com.shustrov38.cargocontrolservice.components.controller;

import com.shustrov38.cargocontrolservice.components.emulator.dto.EmulationDataDto;
import com.shustrov38.cargocontrolservice.components.postgre.service.PostgreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class pipelineKafkaPostgre {

    private PostgreService postgreService;

    @Autowired
    public pipelineKafkaPostgre(
            PostgreService postgreService) {
        this.postgreService = postgreService;
    }

    @KafkaListener(containerFactory = "kafkaListenerContainerFactory", topics = "${kafka.consumer-topic}")
    public void onMessage(EmulationDataDto emulationDataDto) {
        postgreService.createEmulationDataInDB(emulationDataDto);
    }
}
