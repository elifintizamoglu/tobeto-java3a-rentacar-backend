package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.ModelService;
import com.tobeto.rentacar.business.constants.ModelMessages;
import com.tobeto.rentacar.business.dtos.requests.model.CreateModelRequest;
import com.tobeto.rentacar.business.dtos.requests.model.UpdateModelRequest;
import com.tobeto.rentacar.business.dtos.responses.model.*;
import com.tobeto.rentacar.business.rules.BrandBusinessRules;
import com.tobeto.rentacar.business.rules.FuelBusinessRules;
import com.tobeto.rentacar.business.rules.ModelBusinessRules;
import com.tobeto.rentacar.business.rules.TransmissionBusinessRules;
import com.tobeto.rentacar.core.utilities.exceptions.types.ResourceNotFoundException;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.ModelRepository;
import com.tobeto.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {

    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private ModelBusinessRules modelBusinessRules;
    private FuelBusinessRules fuelBusinessRules;
    private TransmissionBusinessRules transmissionBusinessRules;
    private BrandBusinessRules brandBusinessRules;

    @Override
    public CreateModelResponse addModel(CreateModelRequest createModelRequest) {

        modelBusinessRules.modelNameCanNotBeDuplicated(createModelRequest.getName());
        brandBusinessRules.isBrandExists(createModelRequest.getBrandId());
        fuelBusinessRules.isFuelExists(createModelRequest.getFuelId());
        transmissionBusinessRules.isTransmissionExists(createModelRequest.getTransmissionId());

        Model model = this.modelMapperService.forRequest().map(createModelRequest, Model.class);

        model.setId(0);
        model.setCreatedDate(LocalDateTime.now());
        Model createdModel = this.modelRepository.save(model);

        CreateModelResponse createdModelResponse = this.modelMapperService.forResponse().map(createdModel, CreateModelResponse.class);
        return createdModelResponse;
    }

    @Override
    public List<GetAllModelResponse> getAllModels() {

        List<Model> models = modelRepository.findAll();
        List<GetAllModelResponse> response = models.stream().map(model -> modelMapperService.forResponse()
                        .map(model, GetAllModelResponse.class)).collect(Collectors.toList());

        return response;
    }

    @Override
    public void deleteModelById(int id) {

        modelBusinessRules.isModelExists(id);
        modelRepository.deleteById(id);
    }

    @Override
    public UpdateModelResponse updateModelById(int id, UpdateModelRequest updateModelRequest) {

        Model model = modelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ModelMessages.ModelNotFound));

        modelBusinessRules.modelNameCanNotBeDuplicated(updateModelRequest.getName());
        brandBusinessRules.isBrandExists(updateModelRequest.getBrandId());
        fuelBusinessRules.isFuelExists(updateModelRequest.getFuelId());
        transmissionBusinessRules.isTransmissionExists(updateModelRequest.getTransmissionId());

        Model updatedModel = modelMapperService.forRequest().map(updateModelRequest, Model.class);

        model.setName(updatedModel.getName());
        model.setBrand(updatedModel.getBrand());
        model.setFuel(updatedModel.getFuel());
        model.setTransmission(updatedModel.getTransmission());
        model.setUpdatedDate(LocalDateTime.now());
        modelRepository.save(model);

        UpdateModelResponse response = modelMapperService.forResponse().map(model, UpdateModelResponse.class);
        return response;
    }

    @Override
    public GetModelByIdResponse getModelById(int id) {

        Model model = modelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ModelMessages.ModelNotFound));

        GetModelByIdResponse response = modelMapperService.forResponse().map(model, GetModelByIdResponse.class);
        return response;
    }

    @Override
    public List<GetModelsByBrandIdResponse> getModelsByBrandId(int id) {
        List<Model> models = modelRepository.getModelsByBrandId(id);
        List<GetModelsByBrandIdResponse> response = models.stream().map(model -> modelMapperService.forResponse()
                .map(model, GetModelsByBrandIdResponse.class)).collect(Collectors.toList());

        return response;

    }
}
