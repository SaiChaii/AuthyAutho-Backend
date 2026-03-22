package com.example.AuthyAutho.RestControllers;

import com.example.AuthyAutho.Model.DTO.ApiResponse;
import com.example.AuthyAutho.Model.DTO.LoginRequestBody;
import com.example.AuthyAutho.Model.DTO.SignUpRequestBody;
import com.example.AuthyAutho.Service.AuthService;
import com.example.AuthyAutho.logging.AppLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthControllers {

    private final AppLogger _logger = new AppLogger(AuthControllers.class);

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginValidatorController(@RequestBody LoginRequestBody request) {
        _logger.logInformation("Received login request for user: {}", request.getUsername());

        ApiResponse<String> response = authService.loginValidatorService(request);

        if (response.isSuccess()) {
            _logger.logInformation("Login successful for user: {}", request.getUsername());
            return ResponseEntity.ok(response);
        } else {
            _logger.logWarning("Login failed for user: {} - Reason: {}", request.getUsername(), response.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> signUpController(@RequestBody SignUpRequestBody request) {
        _logger.logInformation("Received sign-up request for email: {}", request.getEmailId());

        ApiResponse<String> response = authService.signUpValidatorService(request);

        if (response.isSuccess()) {
            _logger.logInformation("Sign-up successful for email: {}", request.getEmailId());
            return ResponseEntity.ok(response);
        } else {
            _logger.logWarning("Sign-up failed for email: {} - Reason: {}", request.getEmailId(), response.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logOutController() {
        _logger.logInformation("Received logout request. Clearing client-side token.");
        return ResponseEntity.ok(new ApiResponse<String>(true, "Successful", "LogoutSuccessful"));
    }

}
