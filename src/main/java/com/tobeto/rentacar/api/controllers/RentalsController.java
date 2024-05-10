package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.RentalService;
import com.tobeto.rentacar.business.dtos.requests.rental.CreateRentalRequest;
import com.tobeto.rentacar.business.dtos.requests.rental.UpdateRentalRequest;
import com.tobeto.rentacar.business.dtos.responses.rental.CreateRentalResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.GetAllRentalResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.GetRentalByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.UpdateRentalResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rentals")
@AllArgsConstructor
public class RentalsController {

    private RentalService rentalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateRentalResponse addRental(@RequestBody @Valid CreateRentalRequest createRentalRequest) {
        return rentalService.addRental(createRentalRequest);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllRentalResponse> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @DeleteMapping("delete/{id}")
    public void deleteRentalById(@PathVariable int id) {
        rentalService.deleteRentalById(id);
    }

    @PutMapping("update/{id}")
    public UpdateRentalResponse updateRentalById(@PathVariable int id, @RequestBody @Valid UpdateRentalRequest updateRentalRequest) {
        return rentalService.updateRentalById(id, updateRentalRequest);
    }

    @GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetRentalByIdResponse getRentalById(@PathVariable int id) {
        return rentalService.getRentalById(id);
    }
}
