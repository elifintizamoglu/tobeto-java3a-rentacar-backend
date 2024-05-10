package com.tobeto.rentacar.business.dtos.requests.rental;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateRentalRequest {

    @Positive(message = "Car id must be bigger than 0.")
    private int carId;

    @Positive(message = "User id must be bigger than 0.")
    private int userId;

    @FutureOrPresent(message = "Start date must be today or in the future.")
    private LocalDate startDate;

    @Future(message = "End date must be in the future.")
    private LocalDate endDate;
}
