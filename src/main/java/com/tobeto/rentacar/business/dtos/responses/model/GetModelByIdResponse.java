package com.tobeto.rentacar.business.dtos.responses.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetModelByIdResponse {
    private int id;
    private String name;
    private int brandId;
    private String brandName;
    private int fuelId;
    private String fuelName;
    private int transmissionId;
    private String transmissionName;
}
