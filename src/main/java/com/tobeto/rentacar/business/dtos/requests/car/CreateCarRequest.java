package com.tobeto.rentacar.business.dtos.requests.car;

import com.tobeto.rentacar.entities.concretes.CarState;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCarRequest {

    @NotNull(message = "Model year cannot be empty.")
    @Min(value = 1980, message = "Model year cannot be older than 1980.")
    @Max(value = 2024, message = "Model year cannot be newer than 1980.")
    private int modelYear;

    @Pattern(regexp = "^(0[1-9]|[1-7][0-9]|8[01]) (([A-Z]) (\\d{4,5})|([A-Z]{2}) (\\d{3,4})|([A-Z]{3}) (\\d{2,3}))$",
            message = "Please enter a valid plate syntax.")
    private String plate;

    private CarState state;

    @Positive(message = "Daily price must be bigger than 0.")
    private double dailyPrice;

    @Positive(message = "Model id must be bigger than 0.")
    private int modelId;
}
