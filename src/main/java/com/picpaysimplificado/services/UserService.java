package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.dto.request.UserRequestDTO;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.domain.user.dto.response.UserResponseDTO;
import com.picpaysimplificado.exception.InvalidAuthenticationException;
import com.picpaysimplificado.exception.ResourceNotFoundException;
import com.picpaysimplificado.exception.ValueMismatchException;
import com.picpaysimplificado.repositories.UserRepository;
import com.picpaysimplificado.util.Util;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAllUsers() {
        return Util.mapList(userRepository.findAll(), UserResponseDTO.class);
    }

    public User getUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    public void validateTransaction(User sender, BigDecimal amount) throws Throwable {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new InvalidAuthenticationException("User is not authorized to perform transaction");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new ValueMismatchException("Insufficient funds");
        }
    }


    public User save(User user) {
        return userRepository.save(user);
    }

    public User createUser(UserRequestDTO user) {
        return save(Util.convertToEntity(user));
    }


    public User updateUser(Long id, UserRequestDTO user) {
        User userById = getUserById(id);
        return save(Util.mapSourceToEntity(user, userById));
    }

    public void deleteUser(Long id) {
        userRepository.delete(getUserById(id));
    }
}
