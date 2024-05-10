package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.CarService;
import com.tobeto.rentacar.business.dtos.requests.car.CreateCarRequest;
import com.tobeto.rentacar.business.dtos.requests.car.UpdateCarRequest;
import com.tobeto.rentacar.business.dtos.responses.car.CreateCarResponse;
import com.tobeto.rentacar.business.dtos.responses.car.GetAllCarResponse;
import com.tobeto.rentacar.business.dtos.responses.car.GetCarByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.car.UpdateCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cars")
@AllArgsConstructor
public class CarsController {

    private CarService carService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCarResponse addCar(@RequestBody @Valid CreateCarRequest createCarRequest) {
        return carService.addCar(createCarRequest);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCarResponse> getAllCars() {
        return carService.getAllCars();
    }

    @DeleteMapping("delete/{id}")
    public void deleteCarById(@PathVariable int id) {
        carService.deleteCarById(id);
    }

    @PutMapping("update/{id}")
    public UpdateCarResponse updateCarById(@PathVariable int id, @RequestBody @Valid UpdateCarRequest updateCarRequest) {
        return carService.updateCarById(id, updateCarRequest);
    }

    @GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetCarByIdResponse getCarById(int id) {
        return carService.getCarById(id);
    }
}
