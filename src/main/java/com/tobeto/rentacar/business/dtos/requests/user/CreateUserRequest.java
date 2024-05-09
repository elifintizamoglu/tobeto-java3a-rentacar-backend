package com.tobeto.rentacar.business.dtos.requests.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
