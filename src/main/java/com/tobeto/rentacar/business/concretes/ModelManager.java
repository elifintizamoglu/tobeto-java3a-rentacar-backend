package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.ModelService;
import com.tobeto.rentacar.business.constants.ModelMessages;
import com.tobeto.rentacar.business.dtos.requests.model.CreateModelRequest;
import com.tobeto.rentacar.business.dtos.requests.model.UpdateModelRequest;
import com.tobeto.rentacar.business.dtos.responses.model.CreateModelResponse;
import com.tobeto.rentacar.business.dtos.responses.model.GetAllModelResponse;
import com.tobeto.rentacar.business.dtos.responses.model.GetModelByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.model.UpdateModelResponse;
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

        Model model = this.modelMapperService.forRequest()
                .map(createModelRequest, Model.class);
        model.setCreatedDate(LocalDateTime.now());
        model.setId(0);
        Model createdModel = this.modelRepository.save(model);

        CreateModelResponse createdModelResponse = this.modelMapperService.forResponse()
                .map(createdModel, CreateModelResponse.class);
        return createdModelResponse;
    }

    @Override
    public List<GetAllModelResponse> getAllModels() {

        var result = modelRepository.findAll();
        List<GetAllModelResponse> response = result.stream()
                .map(model -> modelMapperService.forResponse()
                        .map(model, GetAllModelResponse.class)).collect(Collectors.toList());

        return response;
    }

    @Override
    public void deleteModelById(int id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ModelMessages.ModelNotFound));
        model.setDeletedDate(LocalDateTime.now());
        modelRepository.delete(model);
    }

    @Override
    public UpdateModelResponse updateModelById(int id, UpdateModelRequest updateModelRequest) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ModelMessages.ModelNotFound));
        Model updatedModel = modelMapperService.forRequest().map(updateModelRequest, Model.class);

        model.setId(id);
        model.setName(updatedModel.getName() != null ? updatedModel.getName() : model.getName());
        model.setBrand(updatedModel.getBrand() != null ? updatedModel.getBrand() : model.getBrand());
        model.setFuel(updatedModel.getFuel() != null ? updatedModel.getFuel() : model.getFuel());
        model.setTransmission(updatedModel.getTransmission() != null ? updatedModel.getTransmission() : model.getTransmission());

        model.setUpdatedDate(LocalDateTime.now());

        modelRepository.save(model);
        UpdateModelResponse response = modelMapperService.forResponse().map(model, UpdateModelResponse.class);
        return response;
    }

    @Override
    public GetModelByIdResponse getModelById(int id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ModelMessages.ModelNotFound));

        GetModelByIdResponse response = modelMapperService.forResponse()
                .map(model, GetModelByIdResponse.class);

        return response;
    }
}
