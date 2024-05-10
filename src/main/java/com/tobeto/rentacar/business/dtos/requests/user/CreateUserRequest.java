package com.tobeto.rentacar.business.dtos.requests.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserRequest {

    @NotBlank(message = "First name cannot be empty.")
    @Size(min = 2, max = 40, message = "First name must be between 2 and 40 characters.")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty.")
    @Size(min = 2, max = 40, message = "Last name must be between 2 and 40 characters.")
    private String lastName;

    @NotBlank(message = "Email cannot be empty.")
    @Email(message = "Email must be in a valid format.")
    private String email;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters.")
    private String password;
}
