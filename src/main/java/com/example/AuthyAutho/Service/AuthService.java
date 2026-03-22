package com.example.AuthyAutho.Service;

import com.example.AuthyAutho.Model.DTO.ApiResponse;
import com.example.AuthyAutho.Model.DTO.LoginRequestBody;
import com.example.AuthyAutho.Model.DTO.SignUpRequestBody;
import com.example.AuthyAutho.Model.Entity.LoginEntity;
import com.example.AuthyAutho.Model.Enums.UserRoles;
import com.example.AuthyAutho.Repository.LoginRepository;
import com.example.AuthyAutho.Service.Utils.SignUpDTOtoEntity;
import com.example.AuthyAutho.config.JwtUtils;
import com.example.AuthyAutho.logging.AppLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final AppLogger _logger = new AppLogger(AuthService.class);

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private SignUpDTOtoEntity signUpDTOtoEntity;

    public ApiResponse<String> loginValidatorService(LoginRequestBody request) {
        _logger.logInformation("Starting login validation for user: {}", request.getUsername());

        boolean check = validateRequestBody(request);
        if (!check) {
            _logger.logWarning("Login validation failed: malformed request body for user: {}", request.getUsername());
            return new ApiResponse<>(false, "Request body is wrong", "Request body is wrong");
        }

        _logger.logInformation("Request body validated. Querying repository for user: {}", request.getUsername());
        Optional<LoginEntity> data = loginRepository.findByUsername(request.getUsername());

        if (data == null) {
            _logger.logWarning("No record found in repository for username: {}", request.getUsername());
            return new ApiResponse<>(false, "No Info", "No user found for the entered username");
        }

        LoginEntity user = data.get();

        if (!user.getPassword().equals(request.getPassword())) {
            _logger.logWarning("Password mismatch for user: {}", request.getUsername());
            return new ApiResponse<>(false, "Auth Failed", "Invalid username or password");
        }

        _logger.logInformation("Password matched. Generating JWT token for user: {}", request.getUsername());
        String token = jwtUtils.generateToken(request.getUsername());
        _logger.logInformation("Login process completed successfully for user: {}", request.getUsername());
        return new ApiResponse<>(true, "Login Successful", token);
    }

    public ApiResponse<String> signUpValidatorService(SignUpRequestBody request) {
        _logger.logInformation("Starting sign-up validation for email: {}", request.getEmailId());

        if (!validateSignUpRequest(request)) {
            _logger.logWarning("Sign-up validation failed: improper request body for email: {}", request.getEmailId());
            return new ApiResponse<>(false, "Improper request body", "Improper request body");
        }

        _logger.logInformation("Request body validated. Checking if user already exists: {}", request.getEmailId());
        Optional<LoginEntity> existingUser = loginRepository.findByUsername(request.getEmailId());

        if (existingUser.isPresent()) {
            _logger.logWarning("Sign-up failed: user already exists with email: {}", request.getEmailId());
            return new ApiResponse<>(false, "User with these credentials is already present", "User already present");
        }

        _logger.logInformation("No existing user found. Creating new user for email: {}", request.getEmailId());
        LoginEntity newUser = signUpDTOtoEntity.ConvertSignUpDTOtoLoginEntity(request);
        loginRepository.save(newUser);
        _logger.logInformation("New user saved successfully for email: {}", request.getEmailId());
        return new ApiResponse<>(true, "Saved", "Saved");
    }

    private boolean validateSignUpRequest(SignUpRequestBody request) {
        if (request == null || request.getEmailId() == null || request.getPassWord() == null || request.getRole() == null) {
            return false;
        }
        if (request.getRole() != UserRoles.Admin || request.getRole() != UserRoles.Supervisor || request.getRole() != UserRoles.Employee) return false;
        return true;
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
