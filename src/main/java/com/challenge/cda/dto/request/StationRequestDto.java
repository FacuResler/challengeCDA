package com.challenge.cda.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StationRequestDto {
   @NotNull
   private String name;
}
