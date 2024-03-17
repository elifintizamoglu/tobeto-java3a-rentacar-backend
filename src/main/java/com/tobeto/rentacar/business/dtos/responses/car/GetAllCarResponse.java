package com.tobeto.rentacar.business.dtos.responses.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllCarResponse {

    private int id;
    private int modelYear;
    private String plate;
    private int state;
    private double dailyPrice;
    private String modelName;
}
