package com.tobeto.rentacar.business.dtos.requests.rental;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateRentalRequest {

    @Positive(message = "Car id must be bigger than 0.")
    private int carId;

    @Positive(message = "User id must be bigger than 0.")
    private int userId;

    @NotNull(message = "Start date cannot be empty.")
    @FutureOrPresent(message = "Start date must be today or in the future.")
    private LocalDate startDate;

    @NotNull(message = "Start date cannot be empty.")
    @Future(message = "End date must be in the future.")
    private LocalDate endDate;
}
