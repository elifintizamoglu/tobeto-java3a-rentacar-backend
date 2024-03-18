package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.core.utilities.exceptions.types.BusinessException;
import com.tobeto.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.tobeto.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class TransmissionBusinessRules {

    TransmissionRepository transmissionRepository;

    public void transmissionNameCanNotBeDuplicated(String transmissionName) {

        Optional<Transmission> transmission =
                transmissionRepository.findByNameIgnoreCase(transmissionName);
        if (transmission.isPresent()) {
            throw new BusinessException("TransmissionExists");
        }
    }

    public void isTransmissionExists(int transmissionId){
        boolean isExists = transmissionRepository.existsById(transmissionId);
        if (!isExists) {
            throw new BusinessException("Transmission does not exist with that id.");
        }
    }

}
