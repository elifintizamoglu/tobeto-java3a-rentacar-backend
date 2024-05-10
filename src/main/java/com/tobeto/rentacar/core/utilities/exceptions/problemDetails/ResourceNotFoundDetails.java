package com.tobeto.rentacar.core.utilities.exceptions.problemDetails;

public class ResourceNotFoundDetails extends ProblemDetails{
    public ResourceNotFoundDetails() {
        setTitle("Resource Not Found");
        setDetail("Data not found.");
        setType("http://mydomain.com/exceptions/validation");
        setStatus("404");
    }
}
