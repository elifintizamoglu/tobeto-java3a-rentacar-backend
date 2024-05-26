package com.tobeto.rentacar.business.dtos.responses.car;

import com.tobeto.rentacar.entities.concretes.enums.CarState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCarResponse {

    private int id;
    private int modelYear;
    private String plate;
    private CarState state;
    private double dailyPrice;
    private int modelId;
    private LocalDateTime createdDate;
}
