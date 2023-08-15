package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Throwable {
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("User is not authorized to perform transaction");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Insufficient funds");
        }
    }
}
