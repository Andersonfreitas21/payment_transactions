package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.dtos.UserDTO;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<User> createUser(UserDTO user){
        return ResponseEntity.ok(service.createUser(user));
    }
}
