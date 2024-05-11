package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.business.constants.FuelMessages;
import com.tobeto.rentacar.core.utilities.exceptions.types.BusinessException;
import com.tobeto.rentacar.dataAccess.abstracts.FuelRepository;
import com.tobeto.rentacar.entities.concretes.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class FuelBusinessRules {

    FuelRepository fuelRepository;

    public void fuelNameCanNotBeDuplicated(String fuelName) {

        String trimmedName = fuelName.trim();
        Optional<Fuel> fuel = fuelRepository.findByNameIgnoreCase(trimmedName);

        if (fuel.isPresent()) {
            throw new BusinessException(FuelMessages.FuelNameAlreadyExists);
        }
    }

    public void isFuelExists(int fuelId) {

        boolean isExists = fuelRepository.existsById(fuelId);
        if (!isExists) {
            throw new BusinessException(FuelMessages.FuelNotFound);
        }
    }

}
