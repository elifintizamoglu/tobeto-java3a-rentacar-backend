package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.model.CreateModelRequest;
import com.tobeto.rentacar.business.dtos.requests.model.UpdateModelRequest;
import com.tobeto.rentacar.business.dtos.responses.model.*;
import com.tobeto.rentacar.core.utilities.results.Result;

import java.util.List;

public interface ModelService {

    CreateModelResponse addModel(CreateModelRequest createModelRequest);

    List<GetAllModelResponse> getAllModels();

    Result deleteModelById(int id);

    UpdateModelResponse updateModelById(int id, UpdateModelRequest updateModelRequest);

    GetModelByIdResponse getModelById(int id);

    List<GetModelsByBrandIdResponse> getModelsByBrandId(int id);


}
