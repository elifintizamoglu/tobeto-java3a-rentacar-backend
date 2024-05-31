package com.tobeto.rentacar.core.utilities.exceptions.problemDetails;

import lombok.Data;

import java.util.Map;

@Data
public class ValidationProblemDetails extends ProblemDetails {
    public ValidationProblemDetails() {
        setTitle("Validation Rule Violation");
        setType("http://mydomain.com/exceptions/validation");
        setStatus("400");
    }
    private Map<String, String> errors;
}
