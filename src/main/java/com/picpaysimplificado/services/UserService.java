package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.dtos.UserDTO;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.exception.ResourceNotFoundException;
import com.picpaysimplificado.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    public UserService(@Qualifier("modelMapper") ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public void validateTransaction(User sender, BigDecimal amount) throws Throwable {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("User is not authorized to perform transaction");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Insufficient funds");
        }
    }

    public User findUserById(Long id) {
        var existingUser = userRepository.findById(id);

        if (existingUser.isEmpty()) {
            throw new ResourceNotFoundException("user not found");
        }
        return existingUser.get();
    }

    public User save(User user) {
        return userRepository.save(user);
    }
    @Transactional
    public void saveAll(List<User> users) {
        userRepository.saveAllAndFlush(users);
    }

    public User createUser(UserDTO user) {
        return save(convertToEntity(user));
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
