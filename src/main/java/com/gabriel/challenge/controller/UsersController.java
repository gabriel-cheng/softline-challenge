package com.gabriel.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.challenge.domain.users.Users;
import com.gabriel.challenge.domain.users.UsersRepository;

@RestController
@RequestMapping("/users")
public class UsersController {
    
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> allUsers = usersRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK)
            .body(allUsers);
    }

}
