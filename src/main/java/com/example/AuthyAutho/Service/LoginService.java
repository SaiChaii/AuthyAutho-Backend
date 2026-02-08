package com.example.AuthyAutho.Service;

import com.example.AuthyAutho.Model.DTO.ApiResponse;
import com.example.AuthyAutho.Model.DTO.LoginRequestBody;
import com.example.AuthyAutho.Model.Entity.LoginEntity;
import com.example.AuthyAutho.Repository.LoginRepository;
import com.example.AuthyAutho.config.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private JwtUtils jwtUtils;

    public ApiResponse<String> loginValidatorService(LoginRequestBody request) {
        boolean check = validateRequestBody(request);

        if(!check) {
            return new ApiResponse<>(false, "Request body is wrong", "Request body is wrong");
        }

        Optional<LoginEntity> data=loginRepository.findByUsername(request.getUsername());

        if(data==null) {
            return new ApiResponse<> (false, "No Info","No user found for the entered username");
        }

        LoginEntity user = data.get();

        if (!user.getPassword().equals(request.getPassword())) {
            return new ApiResponse<>(false, "Auth Failed", "Invalid username or password");
        }

        String token = jwtUtils.generateToken(request.getUsername());
        return new ApiResponse<>(true, "Login Successful", token);

    }

    private boolean validateRequestBody(LoginRequestBody request) {
        // 1. Null check (always do this first to avoid crashes)
        if (request == null || request.getUsername() == null || request.getPassword() == null) {
            return false;
        }

        // 2. Empty string check
        if (request.getUsername().trim().isEmpty() || request.getPassword().trim().isEmpty()) {
            return false;
        }

        // 3. Minimum length check (Standard security practice)
//        if (request.getPassword().length() < 8) {
//            return false;
//        }

        return true;
    }
}
