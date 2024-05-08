package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.model.CreateModelRequest;
import com.tobeto.rentacar.business.dtos.requests.model.UpdateModelRequest;
import com.tobeto.rentacar.business.dtos.responses.model.CreateModelResponse;
import com.tobeto.rentacar.business.dtos.responses.model.GetAllModelResponse;
import com.tobeto.rentacar.business.dtos.responses.model.GetModelByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.model.UpdateModelResponse;

import java.util.List;

public interface ModelService {

    CreateModelResponse addModel(CreateModelRequest createModelRequest);

    List<GetAllModelResponse> getAllModels();

    void deleteModelById(int id);

    UpdateModelResponse updateModelById(int id, UpdateModelRequest updateModelRequest);

    GetModelByIdResponse getModelById(int id);



}
