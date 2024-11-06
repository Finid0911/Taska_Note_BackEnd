package com.example.TaN02.controller;

import com.example.TaN02.entity.Users;
import com.example.TaN02.response.UsersResponse;
import com.example.TaN02.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @GetMapping("/getAllUsers")
    public List<UsersResponse> getAllUsers() {
        List<Users> usersList = usersService.getAllUsers();
        List<UsersResponse> usersResponseList = new ArrayList<>();

        usersList.forEach(users -> {
            usersResponseList.add(new UsersResponse(users));
        });
        return usersResponseList;
    }

    @GetMapping("/getUser/{id}")
    public List<UsersResponse> getUserById(@PathVariable int id) {
        Optional<Users> optionalUsers = usersService.getUserById(id);
        List<UsersResponse> usersResponseList = new ArrayList<>();

        optionalUsers.ifPresent(users -> {
            usersResponseList.add(new UsersResponse(optionalUsers.get()));
        });
        return usersResponseList;
    }

    @GetMapping("/getUserById/{userId}")
    public UsersResponse getUserByUserId(@PathVariable("userId") Integer userId) {
        Users user = usersService.getUserByUserId(userId);

        return new UsersResponse(user);
    }

    @GetMapping
    public List<UsersResponse> getUserById2(@RequestParam Integer userId) {
        Optional<Users> optionalUsers = usersService.getUserById(userId);
        List<UsersResponse> usersResponseList = new ArrayList<>();

        optionalUsers.ifPresent(users -> {
            usersResponseList.add(new UsersResponse(optionalUsers.get()));
        });
        return usersResponseList;
    }

    @GetMapping("/checkIfUserExist/{email}/{password}")
    public Integer checkIfUserExist(@PathVariable("email") String email, @PathVariable("password") String password) {
        return usersService.checkIfUserExist(email, password);
    }

    @GetMapping("/checkUserEmail/{email}")
    public Integer checkUserEmail(@PathVariable("email") String email) {
        return usersService.checkUserEmail(email);
    }

    @GetMapping("/getUserId/{email}/{password}")
    public UsersResponse getUserId(@PathVariable("email") String email, @PathVariable("password") String password) {
        Users user = usersService.findUserId(email, password);

        return new UsersResponse(user);
    }

    @PostMapping("/addNewUser")
    public UsersResponse addNewUser(@Valid @RequestBody UsersResponse usersResponse) {
        Users user = usersService.addNewUser(usersResponse);

        return new UsersResponse(user);
    }

    @PutMapping("/updateUser")
    public UsersResponse updateUser(@Valid @RequestBody UsersResponse usersResponse) {
        Users user = usersService.updateUser(usersResponse);

        return new UsersResponse(user);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam Integer userId) {
        return usersService.deleteUser(userId);
    }
}
