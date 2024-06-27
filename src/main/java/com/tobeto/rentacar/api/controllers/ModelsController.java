package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.ModelService;
import com.tobeto.rentacar.business.dtos.requests.model.CreateModelRequest;
import com.tobeto.rentacar.business.dtos.requests.model.UpdateModelRequest;
import com.tobeto.rentacar.business.dtos.responses.model.*;
import com.tobeto.rentacar.core.utilities.results.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/models", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ModelsController {

    private ModelService modelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateModelResponse addModel(@RequestBody @Valid CreateModelRequest createModelRequest) {
        return modelService.addModel(createModelRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllModelResponse> getAllModels() {
        return modelService.getAllModels();
    }

    @DeleteMapping(path = "delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Result> deleteModelById(@PathVariable int id) {

        Result result = modelService.deleteModelById(id);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateModelResponse updateModelById(@PathVariable int id, @RequestBody @Valid UpdateModelRequest updateModelRequest) {
        return modelService.updateModelById(id, updateModelRequest);
    }

    @GetMapping("getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetModelByIdResponse getModelById(@PathVariable int id) {
        return modelService.getModelById(id);
    }

    @GetMapping(path = "getByBrandId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetModelsByBrandIdResponse> getModelsByBrandId(@PathVariable int id) {
        return modelService.getModelsByBrandId(id);
    }
}
