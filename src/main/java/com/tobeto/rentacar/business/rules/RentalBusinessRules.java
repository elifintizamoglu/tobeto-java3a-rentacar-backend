package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.business.constants.RentalMessages;
import com.tobeto.rentacar.core.utilities.exceptions.types.BusinessException;
import com.tobeto.rentacar.core.utilities.exceptions.types.ResourceNotFoundException;
import com.tobeto.rentacar.dataAccess.abstracts.RentalRepository;
import com.tobeto.rentacar.entities.concretes.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@AllArgsConstructor
@Service
public class RentalBusinessRules {

    RentalRepository rentalRepository;

    public void isRentalExists(int rentalId) {

        boolean isExists = rentalRepository.existsById(rentalId);
        if (!isExists) {
            throw new ResourceNotFoundException(RentalMessages.RentalNotFound);
        }
    }

    public void isCarAvailable(int carId, LocalDate startDate, LocalDate endDate) {

        if (!startDate.isBefore(endDate)) {
            throw new BusinessException(RentalMessages.DatesNotAppropriate);
        }

        List<Rental> conflictingRentals = rentalRepository.findByCarIdAndDateRange(carId, startDate, endDate);
        if (!conflictingRentals.isEmpty()) {
            throw new BusinessException(RentalMessages.CarNotAvailableForSelectedDates);
        }
    }

    public double calculateTotalPrice(double dailyPrice, LocalDate startDate, LocalDate endDate) {

        long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);
        return dailyPrice * numberOfDays;
    }
}
