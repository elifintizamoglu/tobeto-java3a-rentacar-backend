package com.tobeto.rentacar.business.dtos.responses.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
