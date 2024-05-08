package com.tobeto.rentacar.business.dtos.requests.car;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCarRequest {

    @NotNull
    @Min(value = 1950)
    @Max(value = 2024)
    private int modelYear;

    @NotBlank
    private String plate;

    @NotNull
    @Min(value = 1)
    @Max(value = 3)
    private int state;

    @Positive
    private double dailyPrice;

    @NotNull
    private int modelId;
}
