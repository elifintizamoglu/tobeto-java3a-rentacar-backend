package com.tobeto.rentacar.core.utilities.exceptions.handlers;

import com.tobeto.rentacar.core.utilities.exceptions.problemDetails.BusinessProblemDetails;
import com.tobeto.rentacar.core.utilities.exceptions.problemDetails.ResourceNotFoundDetails;
import com.tobeto.rentacar.core.utilities.exceptions.problemDetails.ValidationProblemDetails;
import com.tobeto.rentacar.core.utilities.exceptions.types.BusinessException;
import com.tobeto.rentacar.core.utilities.exceptions.types.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessProblemDetails handleBusinessException(BusinessException exception) {

        BusinessProblemDetails businessProblemDetails = new BusinessProblemDetails();
        businessProblemDetails.setDetail(exception.getMessage());
        return businessProblemDetails;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException exception) {

        Map<String, String> validationErrors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().stream().map(error ->
                validationErrors.put(error.getField(), error.getDefaultMessage())).toList();

        ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
        validationProblemDetails.setErrors(validationErrors);
        validationProblemDetails.setDetail(exception.getMessage());
        return validationProblemDetails;
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResourceNotFoundDetails handleResourceNotFoundException(ResourceNotFoundException exception) {

        ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
        resourceNotFoundDetails.setDetail(exception.getMessage());
        return resourceNotFoundDetails;
    }


}
