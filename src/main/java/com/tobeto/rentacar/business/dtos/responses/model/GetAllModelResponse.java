package com.tobeto.rentacar.business.dtos.responses.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllModelResponse {

    private int id;
    private String name;
    private String brandName;
    private String fuelName;
    private String transmissionName;
}
