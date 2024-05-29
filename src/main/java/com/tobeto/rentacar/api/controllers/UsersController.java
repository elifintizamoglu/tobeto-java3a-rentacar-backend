package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.UserService;
import com.tobeto.rentacar.business.dtos.requests.user.CreateUserRequest;
import com.tobeto.rentacar.business.dtos.requests.user.UpdateUserRequest;
import com.tobeto.rentacar.business.dtos.responses.user.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UsersController {

    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse addUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        return userService.addUser(createUserRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllUserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateUserResponse updateUserById(@PathVariable int id, @RequestBody @Valid UpdateUserRequest updateUserRequest) {
        return userService.updateUserById(id, updateUserRequest);
    }

    @GetMapping(path = "getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetUserByIdResponse getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping(path = "getByEmail/{email}")
    @ResponseStatus(HttpStatus.OK)
    public GetUserByEmailResponse getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }
}
