package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.UserService;
import com.tobeto.rentacar.business.constants.UserMessages;
import com.tobeto.rentacar.business.dtos.requests.user.CreateUserRequest;
import com.tobeto.rentacar.business.dtos.requests.user.UpdateUserRequest;
import com.tobeto.rentacar.business.dtos.responses.user.*;
import com.tobeto.rentacar.business.rules.UserBusinessRules;
import com.tobeto.rentacar.core.utilities.exceptions.types.ResourceNotFoundException;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.UserRepository;
import com.tobeto.rentacar.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserManager implements UserService {

    private UserRepository userRepository;
    private ModelMapperService modelMapperService;
    private UserBusinessRules userBusinessRules;

    @Override
    public CreateUserResponse addUser(CreateUserRequest createUserRequest) {

        userBusinessRules.emailCanNotBeDuplicated(createUserRequest.getEmail());

        User user = modelMapperService.forRequest().map(createUserRequest, User.class);
        user.setCreatedDate(LocalDateTime.now());
        userRepository.save(user);

        CreateUserResponse response = modelMapperService.forResponse().map(user, CreateUserResponse.class);
        return response;
    }

    @Override
    public List<GetAllUserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();

        List<GetAllUserResponse> response = users.stream()
                .map(user -> modelMapperService.forResponse().map(user, GetAllUserResponse.class)).collect(Collectors.toList());
        return response;
    }

    @Override
    public void deleteUserById(int id) {

        userBusinessRules.isUserExists(id);
        userRepository.deleteById(id);
    }

    @Override
    public UpdateUserResponse updateUserById(int id, UpdateUserRequest updateUserRequest) {

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(UserMessages.UserNotFound));

        userBusinessRules.emailCanNotBeDuplicated(updateUserRequest.getEmail());
        User updatedUser = modelMapperService.forRequest().map(updateUserRequest, User.class);

        user.setUpdatedDate(LocalDateTime.now());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        userRepository.save(user);

        UpdateUserResponse response = modelMapperService.forResponse().map(user, UpdateUserResponse.class);
        return response;
    }

    @Override
    public GetUserByIdResponse getUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(UserMessages.UserNotFound));
        GetUserByIdResponse response = modelMapperService.forResponse().map(user, GetUserByIdResponse.class);
        return response;
    }

    @Override
    public GetUserByEmailResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(UserMessages.UserNotFound));
        GetUserByEmailResponse response = modelMapperService.forResponse().map(user, GetUserByEmailResponse.class);
        return response;
    }
}
