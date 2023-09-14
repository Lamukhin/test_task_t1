package com.example.TestTaskT1.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TestTaskT1.dto.StringLineDto;
import com.example.TestTaskT1.service.MainService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api")
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@PostMapping("/send")
	public ResponseEntity<Map<Character, Integer>> solveTheTask(@RequestBody StringLineDto stringLineDto) {
		log.info("Got Sting line {}", stringLineDto.getLine());
		Map<Character, Integer> answer = mainService.solveTheTask(stringLineDto.getLine());
		log.info("Sent answer {}", answer);
		return ResponseEntity.ok(answer);
	}
}
