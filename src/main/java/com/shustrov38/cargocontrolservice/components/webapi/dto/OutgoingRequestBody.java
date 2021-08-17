package com.shustrov38.cargocontrolservice.components.webapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.Instant;

@Data
@AllArgsConstructor
public class OutgoingRequestBody {

    Integer page;

    Integer container;

    Instant time;

    @JsonProperty("par_name")
    String parName;

    @JsonProperty("par_value")
    Double parValue;
}
