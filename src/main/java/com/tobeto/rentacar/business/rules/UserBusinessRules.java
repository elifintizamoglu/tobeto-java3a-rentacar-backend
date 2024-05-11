package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.business.constants.UserMessages;
import com.tobeto.rentacar.core.utilities.exceptions.types.ResourceNotFoundException;
import com.tobeto.rentacar.dataAccess.abstracts.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserBusinessRules {

    UserRepository userRepository;

    public void isUserExists(int userId) {

        boolean isExists = userRepository.existsById(userId);
        if (!isExists) {
            throw new ResourceNotFoundException(UserMessages.UserNotFound);
        }
    }
}
