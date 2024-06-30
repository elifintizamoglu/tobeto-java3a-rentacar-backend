package com.tobeto.rentacar.business.dtos.requests.payment;

import com.tobeto.rentacar.business.dtos.requests.card.CreateCardRequest;
import com.tobeto.rentacar.business.dtos.requests.rental.CreateRentalRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePayAndRentRequest {

    @NotNull(message = "Rental request cannot be null.")
    @Valid
    private CreateRentalRequest rentalRequest;

    @NotNull(message = "Card request cannot be null.")
    @Valid
    private CreateCardRequest cardRequest;

}
