package com.example.AuthyAutho.RestControllers;

import com.example.AuthyAutho.Model.DTO.ApiResponse;
import com.example.AuthyAutho.Service.SummaryService;
import com.example.AuthyAutho.logging.AppLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/summary")
public class SummaryController {

    private final AppLogger _logger = new AppLogger(SummaryController.class);

    @Autowired
    private SummaryService summaryService;

    @GetMapping("/employee")
    public ResponseEntity<ApiResponse<String>> getEmployeeSummaryController() {
        _logger.logInformation("Received request to fetch employee summary.");
        ApiResponse<String> response = summaryService.getEmployeeSummaryController();
        _logger.logInformation("Employee summary fetched successfully.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/manager")
    public ResponseEntity<ApiResponse<String>> getManagerSummaryController() {
        _logger.logInformation("Received request to fetch manager summary.");
        ApiResponse<String> response = summaryService.getManagerSummaryController();
        _logger.logInformation("Manager summary fetched successfully.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin")
    public ResponseEntity<ApiResponse<String>> getAdminSummaryController() {
        _logger.logInformation("Received request to fetch admin summary.");
        ApiResponse<String> response = summaryService.getAdminSummaryController();
        _logger.logInformation("Admin summary fetched successfully.");
        return ResponseEntity.ok(response);
    }
}
