package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.fuel.CreateFuelRequest;
import com.tobeto.rentacar.business.dtos.requests.fuel.UpdateFuelRequest;
import com.tobeto.rentacar.business.dtos.responses.fuel.CreateFuelResponse;
import com.tobeto.rentacar.business.dtos.responses.fuel.GetAllFuelResponse;
import com.tobeto.rentacar.business.dtos.responses.fuel.GetFuelByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.fuel.UpdateFuelResponse;

import java.util.List;

public interface FuelService {
    CreateFuelResponse addFuel(CreateFuelRequest createFuelRequest);

    List<GetAllFuelResponse> getAllFuels();

    void deleteFuelById(int id);

    UpdateFuelResponse updateFuelById(int id, UpdateFuelRequest updateFuelRequest);

    GetFuelByIdResponse getFuelById(int id);

}
