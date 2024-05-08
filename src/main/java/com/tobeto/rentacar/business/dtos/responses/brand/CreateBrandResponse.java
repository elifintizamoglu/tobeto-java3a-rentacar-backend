package com.tobeto.rentacar.business.dtos.responses.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBrandResponse {

    private int id;
    private String name;
    private LocalDateTime createdDate;
}
