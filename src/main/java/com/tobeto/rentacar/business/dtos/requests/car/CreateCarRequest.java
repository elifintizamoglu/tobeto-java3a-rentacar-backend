package com.tobeto.rentacar.business.dtos.requests.car;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCarRequest {

    @NotNull
    @Min(value = 1950)
    @Max(value = 2024)
    private int modelYear;

    @NotNull
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
