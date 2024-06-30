package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.card.CreateCardRequest;
import com.tobeto.rentacar.business.dtos.requests.rental.CreateRentalRequest;
import com.tobeto.rentacar.core.utilities.results.Result;

public interface PaymentService {

    Result payAndRent(CreateRentalRequest rental, CreateCardRequest card);
}
