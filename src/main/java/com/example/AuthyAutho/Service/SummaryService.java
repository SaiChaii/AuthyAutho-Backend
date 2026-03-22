package com.example.AuthyAutho.Service;

import com.example.AuthyAutho.Model.DTO.ApiResponse;
import com.example.AuthyAutho.logging.AppLogger;
import org.springframework.stereotype.Service;

@Service
public class SummaryService {

    private final AppLogger _logger = new AppLogger(SummaryService.class);

    public ApiResponse<String> getEmployeeSummaryController() {
        _logger.logInformation("Fetching employee summary data from service layer.");
        return new ApiResponse<>(true, "This is employee Data", "This is employee Data");
    }

    public ApiResponse<String> getManagerSummaryController() {
        _logger.logInformation("Fetching manager summary data from service layer.");
        return new ApiResponse<>(true, "This is manager Data", "This is manager Data");
    }

    public ApiResponse<String> getAdminSummaryController() {
        _logger.logInformation("Fetching admin summary data from service layer.");
        return new ApiResponse<>(true, "This is admin Data", "This is admin Data");
    }
}
