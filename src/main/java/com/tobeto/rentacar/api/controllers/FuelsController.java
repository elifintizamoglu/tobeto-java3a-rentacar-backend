package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.FuelService;
import com.tobeto.rentacar.business.dtos.requests.fuel.CreateFuelRequest;
import com.tobeto.rentacar.business.dtos.responses.fuel.CreatedFuelResponse;
import com.tobeto.rentacar.business.dtos.responses.fuel.GetAllFuelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/fuels")
@AllArgsConstructor
public class FuelsController {

    private FuelService fuelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedFuelResponse add(@RequestBody @Valid CreateFuelRequest createFuelRequest) {
        return fuelService.add(createFuelRequest);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllFuelResponse> getAll() {
        return fuelService.getAll();
    }
}
