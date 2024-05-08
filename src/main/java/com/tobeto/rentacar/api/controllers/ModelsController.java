package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.ModelService;
import com.tobeto.rentacar.business.dtos.requests.model.CreateModelRequest;
import com.tobeto.rentacar.business.dtos.requests.model.UpdateModelRequest;
import com.tobeto.rentacar.business.dtos.responses.model.CreatedModelResponse;
import com.tobeto.rentacar.business.dtos.responses.model.GetAllModelResponse;
import com.tobeto.rentacar.business.dtos.responses.model.GetModelByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.model.UpdateModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/models")
@AllArgsConstructor
public class ModelsController {

    private ModelService modelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedModelResponse addModel(@RequestBody @Valid CreateModelRequest createModelRequest) {
        return modelService.addModel(createModelRequest);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllModelResponse> getAllModels() {
        return modelService.getAllModels();
    }

    @DeleteMapping("delete/{id}")
    public void deleteModelById(@PathVariable int id) {
        modelService.deleteModelById(id);
    }

    @PutMapping("update/{id}")
    public UpdateModelResponse updateModelById(@PathVariable int id, @RequestBody UpdateModelRequest updateModelRequest) {
        return modelService.updateModelById(id, updateModelRequest);
    }

    @GetMapping("getById/{id}")
    public GetModelByIdResponse getModelById(@PathVariable int id) {
        return modelService.getModelById(id);
    }
}
