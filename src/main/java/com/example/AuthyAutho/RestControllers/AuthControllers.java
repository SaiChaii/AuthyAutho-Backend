package com.example.AuthyAutho.RestControllers;

import com.example.AuthyAutho.Model.DTO.ApiResponse;
import com.example.AuthyAutho.Model.DTO.LoginRequestBody;
import com.example.AuthyAutho.Model.DTO.SignUpRequestBody;
import com.example.AuthyAutho.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthControllers {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginValidatorController(@RequestBody LoginRequestBody request) {
        // 1. Call the service layer
        ApiResponse<String> response = authService.loginValidatorService(request);

        // 2. Check the success flag from your DTO
        if (response.isSuccess()) {
            // Returns 200 OK with the full JSON object
            return ResponseEntity.ok(response);
        } else {
            // Returns 400 Bad Request with the error details in the body
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> signUpController(@RequestBody SignUpRequestBody request){
        ApiResponse<String> response=authService.signUpValidatorService(request);
        if(response.isSuccess()){
            return ResponseEntity.ok(response);
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((response));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logOutController(){
        return ResponseEntity.ok(new ApiResponse<String>(true,"Successful","LogoutSuccessful"));
    }

}
