package com.shustrov38.cargocontrolservice.components.webapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@AllArgsConstructor
public class IncomingRequestBody {

    @JsonProperty("time_after")
    @NotNull
    Instant timeAfter;

    @JsonProperty("time_to")
    @NotNull
    Instant timeTo;

    @JsonProperty("strings_for_page")
    @NotNull
    @Min(1)
    Integer stringsForPage;
}
