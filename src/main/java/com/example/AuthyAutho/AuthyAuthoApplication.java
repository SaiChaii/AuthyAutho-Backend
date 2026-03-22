package com.example.AuthyAutho;

import com.example.AuthyAutho.logging.AppLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthyAuthoApplication {

	private static final AppLogger _logger = new AppLogger(AuthyAuthoApplication.class);

	public static void main(String[] args) {
		_logger.logInformation("AuthyAutho application is starting up...");
		SpringApplication.run(AuthyAuthoApplication.class, args);
		_logger.logInformation("AuthyAutho application started successfully.");
	}

}
