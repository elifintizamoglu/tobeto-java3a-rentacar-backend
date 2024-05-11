package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.TransmissionService;
import com.tobeto.rentacar.business.constants.TransmissionMessages;
import com.tobeto.rentacar.business.dtos.requests.transmission.CreateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.requests.transmission.UpdateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.responses.transmission.CreateTransmissionResponse;
import com.tobeto.rentacar.business.dtos.responses.transmission.GetAllTransmissionResponse;
import com.tobeto.rentacar.business.dtos.responses.transmission.GetTransmissionByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.transmission.UpdateTransmissionResponse;
import com.tobeto.rentacar.business.rules.TransmissionBusinessRules;
import com.tobeto.rentacar.core.utilities.exceptions.types.ResourceNotFoundException;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.tobeto.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransmissionManager implements TransmissionService {

    private TransmissionRepository transmissionRepository;
    private ModelMapperService modelMapperService;
    private TransmissionBusinessRules transmissionBusinessRules;

    @Override
    public CreateTransmissionResponse addTransmission(CreateTransmissionRequest request) {

        transmissionBusinessRules.transmissionNameCanNotBeDuplicated(request.getName());

        Transmission transmission = this.modelMapperService.forRequest().map(request, Transmission.class);
        transmission.setCreatedDate(LocalDateTime.now());
        Transmission createdTransmission = this.transmissionRepository.save(transmission);

        CreateTransmissionResponse response = this.modelMapperService.forResponse().map(createdTransmission, CreateTransmissionResponse.class);
        return response;
    }

    @Override
    public List<GetAllTransmissionResponse> getAllTransmissions() {

        List<Transmission> transmissions = transmissionRepository.findAll();

        List<GetAllTransmissionResponse> response = transmissions.stream().map(transmission -> modelMapperService.forResponse()
                        .map(transmission, GetAllTransmissionResponse.class)).collect(Collectors.toList());
        return response;
    }

    @Override
    public void deleteTransmissionById(int id) {

        transmissionBusinessRules.isTransmissionExists(id);
        transmissionRepository.deleteById(id);
    }

    @Override
    public UpdateTransmissionResponse updateTransmissionById(int id, UpdateTransmissionRequest request) {

        Transmission transmission = transmissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TransmissionMessages.TransmissionNotFound));

        transmissionBusinessRules.transmissionNameCanNotBeDuplicated(request.getName());
        Transmission updatedTr = modelMapperService.forRequest().map(request, Transmission.class);

        transmission.setName(updatedTr.getName());
        transmission.setUpdatedDate(LocalDateTime.now());
        transmissionRepository.save(transmission);

        UpdateTransmissionResponse response = modelMapperService.forResponse().map(transmission, UpdateTransmissionResponse.class);
        return response;
    }

    @Override
    public GetTransmissionByIdResponse getTransmissionById(int id) {

        Transmission transmission = transmissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TransmissionMessages.TransmissionNotFound));

        GetTransmissionByIdResponse response = modelMapperService.forResponse().map(transmission, GetTransmissionByIdResponse.class);
        return response;
    }
}
