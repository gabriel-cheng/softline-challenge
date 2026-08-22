package com.gabriel.challenge.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.gabriel.challenge.domain.users.RequestUsers;
import com.gabriel.challenge.domain.users.Users;
import com.gabriel.challenge.domain.users.UsersRepository;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    UsersController(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<String> registerNewUser(@RequestBody @Validated RequestUsers user) {
        Users newUser = new Users(user);
        newUser.setPass(passwordEncoder.encode(newUser.getPassword()));
        usersRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body("User registered successfully!");
    }

    @GetMapping("/me")
    public ResponseEntity<Users> getOwnUser(@AuthenticationPrincipal Users currentUser) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(currentUser);
    }

    @PatchMapping("/me")
    public ResponseEntity<Users> updateOwnUser(
        @RequestBody @Validated RequestUsers user,
        @AuthenticationPrincipal Users currentUser
    ) {
        if(user.username() != null) {
            currentUser.setUsername(user.username());
        }

        if(user.password() != null) {
            currentUser.setPass(passwordEncoder.encode(user.password()));
        }

        Users userUpdated = usersRepository.save(currentUser);

        return ResponseEntity.status(HttpStatus.OK)
            .body(userUpdated);
    }

}