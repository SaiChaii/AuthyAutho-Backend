package com.example.AuthyAutho.RestControllers;

import com.example.AuthyAutho.Model.DTO.ApiResponse;
import com.example.AuthyAutho.Service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/summary")
public class SummaryController {

    @Autowired
    private SummaryService summaryService;

    @GetMapping("/employee")
    public ResponseEntity<ApiResponse<String>> getEmployeeSummaryController(){
        ApiResponse<String> response=summaryService.getEmployeeSummaryController();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/manager")
    public ResponseEntity<ApiResponse<String>> getManagerSummaryController(){
        ApiResponse<String> response=summaryService.getManagerSummaryController();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin")
    public ResponseEntity<ApiResponse<String>> getAdminSummaryController(){
        ApiResponse<String> response=summaryService.getAdminSummaryController();
        return ResponseEntity.ok(response);
    }
}
