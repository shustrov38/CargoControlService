package com.shustrov38.cargocontrolservice.components.emulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
public class EmulatorConfiguration {

    @JsonProperty("x")
    @NotNull
    @PositiveOrZero
    private Integer containersCount;

    @JsonProperty("t0")
    @NotNull
    private Instant startTime;

    @JsonProperty("t")
    @NotNull
    @PositiveOrZero
    private Double timeout;

    @JsonProperty("d")
    @NotNull
    @PositiveOrZero
    private Double delta;

    @JsonProperty("n")
    @NotNull
    @PositiveOrZero
    private Integer emulationStepsCount;

    @JsonProperty("sensors")
    @NotNull
    private List<Sensor> sensors;

    @JsonProperty("tk")
    @NotNull
    private Integer sendTimeout;

    @Data
    public static class Sensor {
        @JsonProperty("p")
        @NotEmpty
        private String parName;

        @JsonProperty("mp")
        @NotNull
        private Double parValue;

        @JsonProperty("md")
        @NotNull
        @PositiveOrZero
        private Double delta;
    }
}
