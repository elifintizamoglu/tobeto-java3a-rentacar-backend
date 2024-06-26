package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.RentalService;
import com.tobeto.rentacar.business.dtos.requests.rental.CreateRentalRequest;
import com.tobeto.rentacar.business.dtos.requests.rental.UpdateRentalRequest;
import com.tobeto.rentacar.business.dtos.responses.rental.GetAllRentalResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.GetRentalByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.UpdateRentalResponse;
import com.tobeto.rentacar.core.utilities.results.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/rentals", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RentalsController {

    private RentalService rentalService;

    @PostMapping(path = "checkAvailability")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Result> checkAvailability(@RequestBody @Valid CreateRentalRequest createRentalRequest){
        Result result = rentalService.checkAvailability(createRentalRequest);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Result> addRental(@RequestBody CreateRentalRequest createRentalRequest) {
        Result result = rentalService.addRental(createRentalRequest);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllRentalResponse> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRentalById(@PathVariable int id) {
        rentalService.deleteRentalById(id);
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateRentalResponse updateRentalById(@PathVariable int id, @RequestBody @Valid UpdateRentalRequest updateRentalRequest) {
        return rentalService.updateRentalById(id, updateRentalRequest);
    }

    @GetMapping(path = "getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetRentalByIdResponse getRentalById(@PathVariable int id) {
        return rentalService.getRentalById(id);
    }
}
