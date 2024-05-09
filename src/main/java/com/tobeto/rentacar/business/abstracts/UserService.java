package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.user.CreateUserRequest;
import com.tobeto.rentacar.business.dtos.requests.user.UpdateUserRequest;
import com.tobeto.rentacar.business.dtos.responses.user.CreateUserResponse;
import com.tobeto.rentacar.business.dtos.responses.user.GetAllUserResponse;
import com.tobeto.rentacar.business.dtos.responses.user.GetUserByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.user.UpdateUserResponse;

import java.util.List;

public interface UserService {

    CreateUserResponse addUser(CreateUserRequest createUserRequest);

    List<GetAllUserResponse> getAllUsers();

    void deleteUserById(int id);

    UpdateUserResponse updateUserById(int id, UpdateUserRequest updateUserRequest);

    GetUserByIdResponse getUserById(int id);
}
