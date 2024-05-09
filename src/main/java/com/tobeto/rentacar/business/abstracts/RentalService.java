package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.rental.CreateRentalRequest;
import com.tobeto.rentacar.business.dtos.requests.rental.UpdateRentalRequest;
import com.tobeto.rentacar.business.dtos.responses.rental.CreateRentalResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.GetAllRentalResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.GetRentalByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.UpdateRentalResponse;

import java.util.List;

public interface RentalService {

    CreateRentalResponse addRental(CreateRentalRequest createRentalRequest);

    List<GetAllRentalResponse> getAllRentals();

    void deleteRentalById(int id);

    UpdateRentalResponse updateRentalById(int id, UpdateRentalRequest updateRentalRequest);

    GetRentalByIdResponse getRentalById(int id);
}
