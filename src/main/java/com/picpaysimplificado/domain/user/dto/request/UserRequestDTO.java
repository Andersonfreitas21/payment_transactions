package com.picpaysimplificado.domain.user.dto.request;

import com.picpaysimplificado.domain.user.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequestDTO {
    @NotNull @NotBlank(message = "Name is mandatory")
    private String firstName;
    private String lastName;
    @NotNull @NotBlank
    private String document;
    @NotNull @Email @NotBlank(message = "Email is mandatory")
    private String email;
    @NotNull @NotBlank
    private String password;
    private BigDecimal balance;
    private UserType userType;
}
