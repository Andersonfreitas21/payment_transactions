package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.dtos.UserDTO;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO user) throws URISyntaxException {
        User newUser = service.createUser(user);
        URI location = URI.create(String.format("/api/v1/users/%s", newUser.getId()));
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getListUser() {
        return null;
    }
}
