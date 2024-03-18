package com.tobeto.rentacar.business.dtos.requests.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBrandRequest {

    @NotBlank
    @Size(min = 2, max = 30)
    private String name;
}
