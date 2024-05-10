package com.tobeto.rentacar.business.dtos.requests.car;

import com.tobeto.rentacar.entities.concretes.CarState;
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

    private CarState state;

    @Positive
    private double dailyPrice;

    @NotNull
    private int modelId;
}
