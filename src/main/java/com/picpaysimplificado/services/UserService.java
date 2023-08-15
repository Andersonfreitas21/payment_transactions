package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.exception.ResourceNotFoundException;
import com.picpaysimplificado.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Throwable {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("User is not authorized to perform transaction");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Insufficient funds");
        }
    }

    public User findUserById(Long id) {
        var existingUser = this.userRepository.findById(id);

        if (existingUser.isEmpty()) {
            throw new ResourceNotFoundException("user not found");
        }
        return existingUser.get();
    }

    public void save(User user){
        this.userRepository.save(user);
    }
}
