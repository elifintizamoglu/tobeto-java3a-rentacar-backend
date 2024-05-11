package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.RentalService;
import com.tobeto.rentacar.business.constants.RentalMessages;
import com.tobeto.rentacar.business.dtos.requests.rental.CreateRentalRequest;
import com.tobeto.rentacar.business.dtos.requests.rental.UpdateRentalRequest;
import com.tobeto.rentacar.business.dtos.responses.rental.CreateRentalResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.GetAllRentalResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.GetRentalByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.UpdateRentalResponse;
import com.tobeto.rentacar.business.rules.CarBusinessRules;
import com.tobeto.rentacar.business.rules.RentalBusinessRules;
import com.tobeto.rentacar.business.rules.UserBusinessRules;
import com.tobeto.rentacar.core.utilities.exceptions.types.ResourceNotFoundException;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.RentalRepository;
import com.tobeto.rentacar.entities.concretes.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {

    private RentalRepository rentalRepository;
    private ModelMapperService modelMapperService;
    private CarBusinessRules carBusinessRules;
    private UserBusinessRules userBusinessRules;
    private RentalBusinessRules rentalBusinessRules;

    @Override
    public CreateRentalResponse addRental(CreateRentalRequest createRentalRequest) {

        double dailyPrice, totalPrice;

        carBusinessRules.isCarExists(createRentalRequest.getCarId());
        userBusinessRules.isUserExists(createRentalRequest.getUserId());
        rentalBusinessRules.isCarAvailable(createRentalRequest.getCarId(), createRentalRequest.getStartDate(), createRentalRequest.getEndDate());
        dailyPrice = carBusinessRules.getCarDailyPrice(createRentalRequest.getCarId());
        totalPrice = rentalBusinessRules.calculateTotalPrice(dailyPrice, createRentalRequest.getStartDate(), createRentalRequest.getEndDate());

        Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        rental.setTotalPrice(totalPrice);
        rental.setCreatedDate(LocalDateTime.now());
        rentalRepository.save(rental);

        CreateRentalResponse response = modelMapperService.forResponse().map(rental, CreateRentalResponse.class);
        return response;
    }

    @Override
    public List<GetAllRentalResponse> getAllRentals() {

        List<Rental> rentals = rentalRepository.findAll();
        List<GetAllRentalResponse> response = rentals.stream()
                .map(rental -> modelMapperService.forResponse().map(rental, GetAllRentalResponse.class)).collect(Collectors.toList());

        return response;
    }

    @Override
    public void deleteRentalById(int id) {

        rentalBusinessRules.isRentalExists(id);
        rentalRepository.deleteById(id);
    }

    @Override
    public UpdateRentalResponse updateRentalById(int id, UpdateRentalRequest request) {

        double dailyPrice, totalPrice;

        Rental rental = rentalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RentalMessages.RentalNotFound));

        carBusinessRules.isCarExists(request.getCarId());
        userBusinessRules.isUserExists(request.getUserId());
        rentalBusinessRules.isCarAvailable(request.getCarId(), request.getStartDate(), request.getEndDate());
        dailyPrice = carBusinessRules.getCarDailyPrice(request.getCarId());
        totalPrice = rentalBusinessRules.calculateTotalPrice(dailyPrice, request.getStartDate(), request.getEndDate());

        Rental updatedRental = modelMapperService.forRequest().map(request, Rental.class);

        rental.setCar(updatedRental.getCar());
        rental.setUser(updatedRental.getUser());
        rental.setStartDate(updatedRental.getStartDate());
        rental.setEndDate(updatedRental.getEndDate());
        rental.setTotalPrice(totalPrice);
        rental.setUpdatedDate(LocalDateTime.now());
        rentalRepository.save(rental);

        UpdateRentalResponse response = modelMapperService.forResponse().map(rental, UpdateRentalResponse.class);
        return response;
    }

    @Override
    public GetRentalByIdResponse getRentalById(int id) {

        Rental rental = rentalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RentalMessages.RentalNotFound));

        GetRentalByIdResponse response = modelMapperService.forResponse().map(rental, GetRentalByIdResponse.class);
        return response;
    }
}
