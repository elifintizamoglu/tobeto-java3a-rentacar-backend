package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.CardService;
import com.tobeto.rentacar.business.abstracts.PaymentService;
import com.tobeto.rentacar.business.abstracts.RentalService;
import com.tobeto.rentacar.business.constants.PaymentMessages;
import com.tobeto.rentacar.business.dtos.requests.card.CreateCardRequest;
import com.tobeto.rentacar.business.dtos.requests.rental.CreateRentalRequest;
import com.tobeto.rentacar.core.utilities.results.ErrorResult;
import com.tobeto.rentacar.core.utilities.results.Result;
import com.tobeto.rentacar.core.utilities.results.SuccessResult;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {

    private RentalService rentalService;
    private CardService cardService;

    @Override
    @Transactional(rollbackOn = { Exception.class })
    public Result payAndRent(CreateRentalRequest rental, CreateCardRequest card) {

        Result cardResult = cardService.addCard(card);
        if (!cardResult.isSuccess()) {
            return new ErrorResult(cardResult.getMessage());
        }

        Result rentalResult = rentalService.addRental(rental);
        if (!rentalResult.isSuccess()) {
            return new ErrorResult(rentalResult.getMessage());
        }

        return new SuccessResult(PaymentMessages.PaidAndRented);
    }
}
