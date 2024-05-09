package com.tobeto.rentacar.business.dtos.requests.rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateRentalRequest {

    private int carId;
    private int userId;
    private LocalDate startDate;
    private LocalDate endDate;
}
