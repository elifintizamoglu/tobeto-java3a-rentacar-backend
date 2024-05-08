package com.tobeto.rentacar.business.dtos.responses.transmission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateTransmissionResponse {

    private int id;
    private String name;
    private LocalDateTime createdDate;
}
