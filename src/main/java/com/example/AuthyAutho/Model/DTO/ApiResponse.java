package com.example.AuthyAutho.Model.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data; // This can hold your LoginEntity, a String, or any Object
}