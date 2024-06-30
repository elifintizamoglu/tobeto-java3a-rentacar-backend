package com.tobeto.rentacar.business.dtos.requests.card;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCardRequest {

    @NotNull(message = "User ID cannot be null.")
    private int userId;

    @NotBlank(message = "Card number cannot be blank.")
    @Pattern(regexp = "\\d{16}", message = "Card number must be 16 digits.")
    private String cardNumber;

    @NotBlank(message = "Expiration month cannot be blank.")
    @Pattern(regexp = "0[1-9]|1[0-2]", message = "Expiration month must be between 01 and 12.")
    private String expirationMonth;

    @NotBlank(message = "Expiration year cannot be blank.")
    @Pattern(regexp = "\\d{4}", message = "Expiration year must be 4 digits.")
    private String expirationYear;

    @NotBlank(message = "CVV cannot be blank.")
    @Pattern(regexp = "\\d{3}", message = "CVV must be 3 digits.")
    private String cvv;

    @NotBlank(message = "Card holder's full name cannot be blank.")
    @Size(min = 5, max = 100, message = "Card holder's full name must be at most 100 characters.")
    private String cardHolderFullName;
}
