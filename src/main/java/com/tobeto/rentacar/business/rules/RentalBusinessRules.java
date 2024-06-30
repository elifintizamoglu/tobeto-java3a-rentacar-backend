package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.business.constants.RentalMessages;
import com.tobeto.rentacar.core.utilities.exceptions.types.ResourceNotFoundException;
import com.tobeto.rentacar.core.utilities.results.Result;
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

    public Result isCarAvailable(int rentalId, int carId, LocalDate startDate, LocalDate endDate) {

        Result result = new Result(false);

        if (!(startDate.isBefore(endDate) || startDate.isEqual(endDate))) {
            result.setMessage(RentalMessages.DatesNotAppropriate);
            return result;
        }

        List<Rental> conflictingRentals = rentalRepository.findByCarIdAndDateRange(rentalId, carId, startDate, endDate);
        if (!conflictingRentals.isEmpty()) {
            result.setMessage(RentalMessages.CarNotAvailableForSelectedDates);
            return result;
        }
        result.setMessage(RentalMessages.DatesAreAppropriate);
        result.setSuccess(true);
        return result;
    }

    public double calculateTotalPrice(double dailyPrice, LocalDate startDate, LocalDate endDate) {

        double numberOfDays = (double) ChronoUnit.DAYS.between(startDate, endDate) + 1;
        return dailyPrice * numberOfDays;
    }
}
