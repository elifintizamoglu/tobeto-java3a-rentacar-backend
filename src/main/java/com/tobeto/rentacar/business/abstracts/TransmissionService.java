package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.transmission.CreateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.requests.transmission.UpdateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.responses.transmission.CreateTransmissionResponse;
import com.tobeto.rentacar.business.dtos.responses.transmission.GetAllTransmissionResponse;
import com.tobeto.rentacar.business.dtos.responses.transmission.GetTransmissionByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.transmission.UpdateTransmissionResponse;

import java.util.List;

public interface TransmissionService {
    CreateTransmissionResponse addTransmission(CreateTransmissionRequest createTransmissionRequest);

    List<GetAllTransmissionResponse> getAllTransmissions();

    void deleteTransmissionById(int id);

    UpdateTransmissionResponse updateTransmissionById(int id, UpdateTransmissionRequest updateTransmissionRequest);

    GetTransmissionByIdResponse getTransmissionById(int id);

}
