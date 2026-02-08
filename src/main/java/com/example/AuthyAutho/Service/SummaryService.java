package com.example.AuthyAutho.Service;

import com.example.AuthyAutho.Model.DTO.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public class SummaryService {
    public ApiResponse<String> getEmployeeSummaryController() {
        return new ApiResponse<>(true,"This is employee Data","This is employee Data");
    }

    public ApiResponse<String> getManagerSummaryController() {
        return new ApiResponse<>(true,"This is manager Data","This is manager Data");
    }

    public ApiResponse<String> getAdminSummaryController() {
        return new ApiResponse<>(true,"This is admin Data","This is admin Data");
    }
}
