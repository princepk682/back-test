package com.y2tek.backtest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
@Slf4j
public class HealthCheckController {

	@GetMapping("health-check")
	public ResponseEntity<String> healthCheck() {
		return ResponseEntity.status(HttpStatus.OK).body("{\"Status \":\"Okay \"}");
	}

}
