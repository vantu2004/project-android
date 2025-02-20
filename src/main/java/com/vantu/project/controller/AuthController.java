package com.vantu.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vantu.project.model.Users;
import com.vantu.project.requests.RegisterRequest;
import com.vantu.project.responses.RegisterResponse;
import com.vantu.project.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserService userService;

	@GetMapping("/test")
	public String getTestPage() {
		return "Test Success!";
	}

	@PostMapping("/register")
	public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
		RegisterResponse registerResponse = userService.register(registerRequest);
		return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
	}

	@PostMapping("/verify")
	public ResponseEntity<?> verifyUser(@RequestParam String email, @RequestParam String otp) {
		try {
			userService.verify(email, otp);
			return new ResponseEntity<>("User verified successfully", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
		Users users = userService.login(email, password);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PostMapping("/forgot")
	public ResponseEntity<?> sendResetPasswordOtp(@RequestParam String email) {
		try {
			userService.sendResetPasswordOtp(email);
			return new ResponseEntity<>("Da gui ma OTP ve mail cua ban, vui long kiem tra", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestParam String email, @RequestParam String newPassword,
			@RequestParam String otp) {
		try {
			userService.resetPassword(email, newPassword, otp);
			return new ResponseEntity<>("Thay doi mat khau thanh cong", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
