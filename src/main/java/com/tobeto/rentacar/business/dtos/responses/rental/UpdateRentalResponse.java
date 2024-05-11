package com.tobeto.rentacar.business.dtos.responses.rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateRentalResponse {
    private int id;
    private int carId;
    private int userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;
}
