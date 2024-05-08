package com.tobeto.rentacar.business.dtos.requests.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateModelRequest {
    @NotBlank
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    private int brandId;

    @NotNull
    private int fuelId;

    @NotNull
    private int transmissionId;
}
