package com.tobeto.rentacar.business.dtos.responses.transmission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateTransmissionResponse {
    private int id;
    private String name;
}
