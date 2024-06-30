package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.CardService;
import com.tobeto.rentacar.business.dtos.requests.card.CreateCardRequest;
import com.tobeto.rentacar.core.utilities.results.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/cards", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CardsController {

    private CardService cardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Result> addCard(@RequestBody @Valid CreateCardRequest createCardRequest) {
        Result result = cardService.addCard(createCardRequest);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }
}
