package com.tobeto.rentacar.business.dtos.responses.fuel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetFuelByIdResponse {
    private int id;
    private String name;
}
