package com.shustrov38.cargocontrolservice.components.postgre.service;

import com.shustrov38.cargocontrolservice.components.emulator.dto.EmulationDataDto;
import com.shustrov38.cargocontrolservice.components.postgre.models.EmulationData;
import com.shustrov38.cargocontrolservice.components.postgre.repository.EmulationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

import static com.shustrov38.cargocontrolservice.components.postgre.mapper.Mappers.EMULATION_DATA_MAPPER;

@Service
public class PostgreService {
    private EmulationDataRepository emulationDataRepository;

    @Autowired
    public PostgreService(
            EmulationDataRepository emulationDataRepository) {
        this.emulationDataRepository = emulationDataRepository;
    }

    public List<EmulationDataDto> getEmulationDataFromDbFilteredByTimeInterval(Instant startTime, Instant endTime) {
        return emulationDataRepository.getAll().stream()
                .peek(data -> data.getContainers()
                        .forEach(container ->
                                container.setState(
                                        container.getState().stream()
                                                .filter(state ->
                                                        startTime.compareTo(state.getTime()) <= 0 &&
                                                        endTime.compareTo(state.getTime()) >= 0
                                                ).toList()
                                )
                        )
                ).map(EMULATION_DATA_MAPPER::toDto).toList();
    }

    public Integer getEmulationDataCount() {
        return emulationDataRepository.getEmulationDataCount();
    }

    @Transactional
    public EmulationData createEmulationDataInDB(EmulationDataDto emulationDataDto) {
        return emulationDataRepository.save(EMULATION_DATA_MAPPER.toModel(emulationDataDto));
    }
}
