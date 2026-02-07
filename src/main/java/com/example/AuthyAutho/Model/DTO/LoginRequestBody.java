package com.example.AuthyAutho.Model.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestBody {
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
}
