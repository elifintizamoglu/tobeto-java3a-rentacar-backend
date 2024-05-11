package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.business.constants.UserMessages;
import com.tobeto.rentacar.core.utilities.exceptions.types.BusinessException;
import com.tobeto.rentacar.core.utilities.exceptions.types.ResourceNotFoundException;
import com.tobeto.rentacar.dataAccess.abstracts.UserRepository;
import com.tobeto.rentacar.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserBusinessRules {

    UserRepository userRepository;

    public void emailCanNotBeDuplicated(String email) {

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new BusinessException(UserMessages.EmailAlreadyExists);
        }
    }

    public void isUserExists(int userId) {

        boolean isExists = userRepository.existsById(userId);
        if (!isExists) {
            throw new ResourceNotFoundException(UserMessages.UserNotFound);
        }
    }
}
