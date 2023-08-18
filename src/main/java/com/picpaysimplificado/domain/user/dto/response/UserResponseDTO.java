package com.picpaysimplificado.domain.user.dto.response;

import com.picpaysimplificado.domain.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String document;
    private String email;
    private BigDecimal balance;
    private UserType userType;
}
