package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.transmission.CreateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.responses.transmission.CreatedTransmissionResponse;
import com.tobeto.rentacar.business.dtos.responses.transmission.GetAllTransmissionResponse;

import java.util.List;

public interface TransmissionService {
    CreatedTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest);

    List<GetAllTransmissionResponse> getAll();
}
