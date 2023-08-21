package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.user.dto.request.UserRequestDTO;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.dto.response.UserResponseDTO;
import com.picpaysimplificado.services.UserService;
import com.picpaysimplificado.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService service;

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable("id") Long id) {
        return Util.convertToDto(service.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Validated UserRequestDTO user) throws URISyntaxException {
        User newUser = service.createUser(user);
        URI location = URI.create(String.format("/api/v1/users/%s", newUser.getId()));
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable("id") Long id, @RequestBody @Validated UserRequestDTO user) {
        return Util.convertToDto(service.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
