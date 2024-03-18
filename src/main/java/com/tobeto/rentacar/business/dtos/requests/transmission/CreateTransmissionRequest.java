package com.tobeto.rentacar.business.dtos.requests.transmission;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateTransmissionRequest {

    @NotBlank
    @Size(min = 2, max = 30)
    private String name;
}
