package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.dtos.NotificationDTO;
import com.picpaysimplificado.domain.user.User;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@AllArgsConstructor
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private static final String URL_NOTIFY = "https://o368l.wiremockapi.cloud/notify";
    private final RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity(URL_NOTIFY, notificationRequest, String.class);
        log.info("Info log message " + Objects.requireNonNull(notificationResponse.getBody()).equalsIgnoreCase("message"));

        if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
            log.error("Serviço de notificação está fora do ar");
            throw new Exception("Notification service is down");

        }

    }
}
