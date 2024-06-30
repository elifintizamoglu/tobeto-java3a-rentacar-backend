package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.PaymentService;
import com.tobeto.rentacar.business.dtos.requests.payment.CreatePayAndRentRequest;
import com.tobeto.rentacar.core.utilities.results.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/payments", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PaymentsController {

    private PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Result> payAndRent(@RequestBody @Valid CreatePayAndRentRequest request) {

        try {
            Result result = paymentService.payAndRent(request.getRentalRequest(), request.getCardRequest());
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result(false, e.getMessage()));
        }
    }
}
