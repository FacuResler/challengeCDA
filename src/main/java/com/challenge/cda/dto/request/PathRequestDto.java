package com.challenge.cda.dto.request;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class PathRequestDto {
    @NotNull
    private Double cost;
    @NotNull
    private Long   sourceId;
    @NotNull
    private Long destinationId;
}
