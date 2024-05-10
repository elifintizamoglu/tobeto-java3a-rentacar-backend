package com.tobeto.rentacar.business.dtos.requests.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateModelRequest {

    @NotBlank(message = "Name cannot be empty.")
    @Size(min = 2, max = 40, message = "Name must be between 2 and 40 characters.")
    private String name;

    @Positive(message = "Brand id must be bigger than 0.")
    private int brandId;

    @Positive(message = "Fuel id must be bigger than 0.")
    private int fuelId;

    @Positive(message = "Transmission id must be bigger than 0.")
    private int transmissionId;
}
