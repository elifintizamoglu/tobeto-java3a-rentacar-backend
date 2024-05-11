package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.TransmissionService;
import com.tobeto.rentacar.business.dtos.requests.transmission.CreateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.requests.transmission.UpdateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.responses.transmission.CreateTransmissionResponse;
import com.tobeto.rentacar.business.dtos.responses.transmission.GetAllTransmissionResponse;
import com.tobeto.rentacar.business.dtos.responses.transmission.GetTransmissionByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.transmission.UpdateTransmissionResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transmissions")
@AllArgsConstructor
public class TransmissionsController {

    private TransmissionService transmissionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateTransmissionResponse addTransmission(@RequestBody @Valid CreateTransmissionRequest createTransmissionRequest) {
        return transmissionService.addTransmission(createTransmissionRequest);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllTransmissionResponse> getAllTransmissions() {
        return transmissionService.getAllTransmissions();
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransmissionById(@PathVariable int id) {
        transmissionService.deleteTransmissionById(id);
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateTransmissionResponse updateTransmissionById(@PathVariable int id, @RequestBody @Valid UpdateTransmissionRequest updateTransmissionRequest) {
        return transmissionService.updateTransmissionById(id, updateTransmissionRequest);
    }

    @GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GetTransmissionByIdResponse getTransmissionById(@PathVariable int id) {
        return transmissionService.getTransmissionById(id);
    }
}
