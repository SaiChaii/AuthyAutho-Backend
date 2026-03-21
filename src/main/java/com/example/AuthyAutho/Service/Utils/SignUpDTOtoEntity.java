package com.example.AuthyAutho.Service.Utils;

import com.example.AuthyAutho.Model.DTO.SignUpRequestBody;
import com.example.AuthyAutho.Model.Entity.LoginEntity;
import org.springframework.stereotype.Component;

@Component
public class SignUpDTOtoEntity {
    public LoginEntity  ConvertSignUpDTOtoLoginEntity(SignUpRequestBody request){
        return LoginEntity.builder()
                .username(request.getEmailId())
                .password(request.getPassWord())
                .role(request.getRole())
                // Notice we don't set 'id' here because Hibernate handles it
                .build();
    }
}
