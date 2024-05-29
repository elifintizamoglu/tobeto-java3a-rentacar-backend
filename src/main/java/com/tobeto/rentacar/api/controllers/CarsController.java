package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.CarService;
import com.tobeto.rentacar.business.dtos.requests.car.CreateCarRequest;
import com.tobeto.rentacar.business.dtos.requests.car.UpdateCarRequest;
import com.tobeto.rentacar.business.dtos.responses.car.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/cars", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CarsController {

    private CarService carService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCarResponse addCar(@RequestBody @Valid CreateCarRequest createCarRequest) {
        return carService.addCar(createCarRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCarResponse> getAllCars() {
        return carService.getAllCars();
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCarById(@PathVariable int id) {
        carService.deleteCarById(id);
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateCarResponse updateCarById(@PathVariable int id, @RequestBody @Valid UpdateCarRequest updateCarRequest) {
        return carService.updateCarById(id, updateCarRequest);
    }

    @GetMapping(path = "getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCarByIdResponse getCarById(@PathVariable int id) {
        return carService.getCarById(id);
    }

    @GetMapping(path = "getByFilters")
    @ResponseStatus(HttpStatus.OK)
    public List<GetCarsByFiltersResponse> getCarsByFilters(@RequestParam(required = false) Integer brandId,
                                                           @RequestParam(required = false) Integer modelId,
                                                           @RequestParam(required = false) Integer fuelId,
                                                           @RequestParam(required = false) Integer transmissionId) {

        return carService.getCarsByFilters(brandId, modelId, fuelId, transmissionId);
    }
}
