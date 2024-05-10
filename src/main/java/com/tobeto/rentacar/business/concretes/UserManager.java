package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.UserService;
import com.tobeto.rentacar.business.constants.UserMessages;
import com.tobeto.rentacar.business.dtos.requests.user.CreateUserRequest;
import com.tobeto.rentacar.business.dtos.requests.user.UpdateUserRequest;
import com.tobeto.rentacar.business.dtos.responses.user.CreateUserResponse;
import com.tobeto.rentacar.business.dtos.responses.user.GetAllUserResponse;
import com.tobeto.rentacar.business.dtos.responses.user.GetUserByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.user.UpdateUserResponse;
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

    @Override
    public CreateUserResponse addUser(CreateUserRequest createUserRequest) {
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
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(UserMessages.UserNotFound));
        user.setDeletedDate(LocalDateTime.now());
        userRepository.delete(user);
    }

    @Override
    public UpdateUserResponse updateUserById(int id, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(UserMessages.UserNotFound));
        User updatedUser = modelMapperService.forRequest().map(updateUserRequest, User.class);

        user.setUpdatedDate(LocalDateTime.now());
        user.setFirstName(updatedUser.getFirstName() != null ? updatedUser.getFirstName() : user.getFirstName());
        user.setLastName(updatedUser.getLastName() != null ? updatedUser.getLastName() : user.getLastName());
        user.setEmail(updatedUser.getEmail() != null ? updatedUser.getEmail() : user.getEmail());
        user.setPassword(updatedUser.getPassword() != null ? updatedUser.getPassword() : user.getPassword());

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
}
