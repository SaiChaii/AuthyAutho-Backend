package com.example.AuthyAutho.Model.DTO;

import com.example.AuthyAutho.Model.Enums.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpRequestBody {
    String emailId;
    String passWord;
    UserRoles role;
}
