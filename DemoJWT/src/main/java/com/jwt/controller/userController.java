package com.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {
	
	@GetMapping("/access")
	public ResponseEntity<String> afterLogin(){
		return ResponseEntity.ok("access the protected api");
	}

}
