package com.tobeto.rentacar.business.dtos.responses.car;


import com.tobeto.rentacar.entities.concretes.enums.CarState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetCarsByFiltersResponse {
    private int id;
    private int modelYear;
    private String plate;
    private CarState state;
    private double dailyPrice;
    private String imagePath;
    private String brandName;
    private String modelName;
    private String fuelName;
    private String transmissionName;
}
