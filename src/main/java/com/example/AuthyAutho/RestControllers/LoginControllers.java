package com.example.AuthyAutho.RestControllers;

import com.example.AuthyAutho.Model.DTO.ApiResponse;
import com.example.AuthyAutho.Model.DTO.LoginRequestBody;
import com.example.AuthyAutho.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginControllers {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginValidatorController(@RequestBody LoginRequestBody request) {
        // 1. Call the service layer
        ApiResponse<String> response = loginService.loginValidatorService(request);

        // 2. Check the success flag from your DTO
        if (response.isSuccess()) {
            // Returns 200 OK with the full JSON object
            return ResponseEntity.ok(response);
        } else {
            // Returns 400 Bad Request with the error details in the body
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
