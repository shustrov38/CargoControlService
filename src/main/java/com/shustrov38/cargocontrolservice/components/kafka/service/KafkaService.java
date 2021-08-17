package com.shustrov38.cargocontrolservice.components.kafka.service;

import com.shustrov38.cargocontrolservice.components.kafka.config.KafkaConfig;
import com.shustrov38.cargocontrolservice.components.emulator.dto.EmulationDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class KafkaService {

    private KafkaConfig kafkaConfig;
    private KafkaTemplate<String, EmulationDataDto> kafkaTemplate;

    @Autowired
    public KafkaService(
            KafkaConfig kafkaConfig,
            KafkaTemplate<String, EmulationDataDto> kafkaTemplate) {
        this.kafkaConfig = kafkaConfig;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEmulationData(EmulationDataDto emulationData) {
        String uniqueKey = "emulation#" + Instant.now().toString();

        kafkaTemplate.send(
                kafkaConfig.getConsumerTopic(),
                uniqueKey,
                emulationData
        );

        kafkaTemplate.send(
                kafkaConfig.getProducerTopic(),
                uniqueKey,
                emulationData
        );
    }
}
