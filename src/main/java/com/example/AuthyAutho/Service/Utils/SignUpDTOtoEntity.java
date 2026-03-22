package com.example.AuthyAutho.Service.Utils;

import com.example.AuthyAutho.Model.DTO.SignUpRequestBody;
import com.example.AuthyAutho.Model.Entity.LoginEntity;
import com.example.AuthyAutho.logging.AppLogger;
import org.springframework.stereotype.Component;

@Component
public class SignUpDTOtoEntity {

    private final AppLogger _logger = new AppLogger(SignUpDTOtoEntity.class);

    public LoginEntity ConvertSignUpDTOtoLoginEntity(SignUpRequestBody request) {
        _logger.logInformation("Converting SignUpRequestBody DTO to LoginEntity for email: {}", request.getEmailId());
        LoginEntity entity = LoginEntity.builder()
                .username(request.getEmailId())
                .password(request.getPassWord())
                .role(request.getRole())
                // Notice we don't set 'id' here because Hibernate handles it
                .build();
        _logger.logInformation("DTO conversion complete for email: {}", request.getEmailId());
        return entity;
    }
}
