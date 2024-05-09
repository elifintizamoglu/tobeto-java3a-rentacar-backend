package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.RentalService;
import com.tobeto.rentacar.business.dtos.requests.rental.CreateRentalRequest;
import com.tobeto.rentacar.business.dtos.requests.rental.UpdateRentalRequest;
import com.tobeto.rentacar.business.dtos.responses.rental.CreateRentalResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.GetAllRentalResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.GetRentalByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.UpdateRentalResponse;
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

    @Override
    public CreateRentalResponse addRental(CreateRentalRequest createRentalRequest) {
        Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
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
        Rental rental = rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no rental with this id."));
        rental.setDeletedDate(LocalDateTime.now());
        rentalRepository.delete(rental);
    }

    @Override
    public UpdateRentalResponse updateRentalById(int id, UpdateRentalRequest updateRentalRequest) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no rental with this id."));
        Rental updatedRental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);

        rental.setCar(updatedRental.getCar() != null ? updatedRental.getCar() : rental.getCar());
        rental.setUser(updatedRental.getUser() != null ? updatedRental.getUser() : rental.getUser());
        rental.setStartDate(updatedRental.getStartDate() != null ? updatedRental.getStartDate() : rental.getStartDate());
        rental.setEndDate(updatedRental.getEndDate() != null ? updatedRental.getEndDate() : rental.getEndDate());
        rental.setTotalPrice(updatedRental.getTotalPrice() != 0 ? updatedRental.getTotalPrice() : rental.getTotalPrice());

        rental.setUpdatedDate(LocalDateTime.now());

        rentalRepository.save(rental);
        UpdateRentalResponse response = modelMapperService.forResponse().map(rental, UpdateRentalResponse.class);
        return response;
    }

    @Override
    public GetRentalByIdResponse getRentalById(int id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no rental with this id"));
        GetRentalByIdResponse response = modelMapperService.forResponse().map(rental, GetRentalByIdResponse.class);
        return response;
    }
}
