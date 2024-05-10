package com.tobeto.rentacar.business.dtos.requests.fuel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateFuelRequest {

    @NotBlank(message = "Name cannot be empty.")
    @Size(min = 2, max = 40, message = "Name must be between 2 and 40 characters.")
    private String name;
}
