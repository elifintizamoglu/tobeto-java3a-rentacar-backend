package com.tobeto.rentacar.business.dtos.responses.rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateRentalResponse {
    private int id;
    private int carId;
    private String carPlate;
    private int userId;
    private String userFirstName;
    private String userLastName;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;
}
