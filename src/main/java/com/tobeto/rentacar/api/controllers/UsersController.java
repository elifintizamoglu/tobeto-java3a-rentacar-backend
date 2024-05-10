package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.UserService;
import com.tobeto.rentacar.business.dtos.requests.user.CreateUserRequest;
import com.tobeto.rentacar.business.dtos.requests.user.UpdateUserRequest;
import com.tobeto.rentacar.business.dtos.responses.user.CreateUserResponse;
import com.tobeto.rentacar.business.dtos.responses.user.GetAllUserResponse;
import com.tobeto.rentacar.business.dtos.responses.user.GetUserByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.user.UpdateUserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UsersController {

    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse addUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        return userService.addUser(createUserRequest);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllUserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("delete/{id}")
    public void deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
    }

    @PutMapping("update/{id}")
    public UpdateUserResponse updateUserById(@PathVariable int id, @RequestBody @Valid UpdateUserRequest updateUserRequest) {
        return userService.updateUserById(id, updateUserRequest);
    }

    @GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetUserByIdResponse getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }
}
