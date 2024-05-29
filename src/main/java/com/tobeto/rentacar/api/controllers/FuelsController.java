package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.FuelService;
import com.tobeto.rentacar.business.dtos.requests.fuel.CreateFuelRequest;
import com.tobeto.rentacar.business.dtos.requests.fuel.UpdateFuelRequest;
import com.tobeto.rentacar.business.dtos.responses.fuel.CreateFuelResponse;
import com.tobeto.rentacar.business.dtos.responses.fuel.GetAllFuelResponse;
import com.tobeto.rentacar.business.dtos.responses.fuel.GetFuelByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.fuel.UpdateFuelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/fuels", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class FuelsController {

    private FuelService fuelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateFuelResponse addFuel(@RequestBody @Valid CreateFuelRequest createFuelRequest) {
        return fuelService.addFuel(createFuelRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllFuelResponse> getAllFuels() {
        return fuelService.getAllFuels();
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFuelById(@PathVariable int id) {
        fuelService.deleteFuelById(id);
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateFuelResponse updateFuelById(@PathVariable int id, @RequestBody @Valid UpdateFuelRequest updateFuelRequest) {
        return fuelService.updateFuelById(id, updateFuelRequest);
    }

    @GetMapping(path = "getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetFuelByIdResponse getFuelById(@PathVariable int id) {
        return fuelService.getFuelById(id);
    }
}
