package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.transaction.dto.request.TransactionDTO;
import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.exception.InvalidAuthorizationException;
import com.picpaysimplificado.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TransactionService {
    private static final String URL_AUTHORIZATION = "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6";
    private final UserService userService;
    private final TransactionRepository repository;
    private final RestTemplate restTemplate;
    private final NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transaction) {
        User sender = userService.getUserById(transaction.senderId());
        User receiver = userService.getUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized = authorizeTransaction();

        if (!isAuthorized) {
            throw new InvalidAuthorizationException("unauthorized transaction");
        }

        Transaction transactionResponse = Transaction
                .builder()
                .amount(transaction.value())
                .sender(sender)
                .receiver(receiver)
                .timestemp(LocalDateTime.now())
                .build();

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        repository.save(transactionResponse);
        userService.saveAll(List.of(sender, receiver));

        notificationService.sendNotification(sender, "Successfully completed transaction");
        notificationService.sendNotification(receiver, "Successfully received transaction");
        return transactionResponse;
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
            throw new InvalidAuthorizationException("Error calling API: " + e.getMessage());
        }
    }
}
