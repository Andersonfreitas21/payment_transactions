package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.transaction.dto.request.TransactionDTO;
import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TransactionService {
    private static final String URL_AUTHORIZATION = "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6";
    private static UserService userService;
    private static TransactionRepository repository;

    private static RestTemplate restTemplate;

    public void createTransaction(TransactionDTO transactionDto) throws Exception {
        User sender = userService.getUserById(transactionDto.senderId());
        User receiver = userService.getUserById(transactionDto.receiveId());

        boolean isAuthorized = authorizeTransaction();

        if (!isAuthorized) {
            throw new Exception("unauthorized transactionDto");
        }

        Transaction transaction = Transaction
                .builder()
                .amount(transactionDto.value())
                .sender(sender)
                .receiver(receiver)
                .timestemp(LocalDateTime.now())
                .build();

        sender.setBalance(sender.getBalance().subtract(transactionDto.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDto.value()));

        repository.save(transaction);
        userService.save(sender);
        userService.save(receiver);

    }

    public boolean authorizeTransaction() {
        try {
            var responseAuthorization =
                    restTemplate.getForEntity(URL_AUTHORIZATION, Map.class);
            if (responseAuthorization.getStatusCode() == HttpStatus.OK) {
                String message = (String) Objects.requireNonNull(responseAuthorization.getBody()).get("message");
                return "Autorizado".equalsIgnoreCase(message);
            } else return false;

        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error calling API: " + e.getMessage());
        }


    }
}
