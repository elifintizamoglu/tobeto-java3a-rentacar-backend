package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.car.CreateCarRequest;
import com.tobeto.rentacar.business.dtos.requests.car.UpdateCarRequest;
import com.tobeto.rentacar.business.dtos.responses.car.CreateCarResponse;
import com.tobeto.rentacar.business.dtos.responses.car.GetAllCarResponse;
import com.tobeto.rentacar.business.dtos.responses.car.GetCarByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.car.UpdateCarResponse;

import java.util.List;

public interface CarService {

    CreateCarResponse addCar(CreateCarRequest createCarRequest);

    List<GetAllCarResponse> getAllCars();

    void deleteCarById(int id);

    UpdateCarResponse updateCarById(int id, UpdateCarRequest updateCarRequest);

    GetCarByIdResponse getCarById(int id);
}
