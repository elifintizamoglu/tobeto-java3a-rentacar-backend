package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.car.CreateCarRequest;
import com.tobeto.rentacar.business.dtos.responses.car.CreatedCarResponse;
import com.tobeto.rentacar.business.dtos.responses.car.GetAllCarResponse;

import java.util.List;

public interface CarService {

    CreatedCarResponse add(CreateCarRequest createCarRequest);

    List<GetAllCarResponse> getAll();
}
