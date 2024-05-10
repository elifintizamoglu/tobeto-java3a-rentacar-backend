package com.tobeto.rentacar.business.dtos.responses.car;

import com.tobeto.rentacar.entities.concretes.CarState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllCarResponse {

    private int id;
    private int modelYear;
    private String plate;
    private CarState state;
    private double dailyPrice;
    private int modelId;
    private String modelName;
}
