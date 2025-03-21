package com.vantu.project.service.IMPL;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.vantu.project.model.Users;
import com.vantu.project.repository.UsersRepository;
import com.vantu.project.requests.RegisterRequest;
import com.vantu.project.responses.RegisterResponse;
import com.vantu.project.service.UserService;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UsersRepository usersRepository;
	private final EmailService emailService;

	@Override
	public Users login(String email, String password) {
		Users user = usersRepository.findByEmail(email);
		if (user != null && user.isVerified() && user.getPassword().equals(password)) {
			return user;
		} else {
			throw new RuntimeException("Login Fail!");
		}
	}

	@Override
	public RegisterResponse register(RegisterRequest registerRequest) {
		Users existingUser = usersRepository.findByEmail(registerRequest.getEmail());
//		if (existingUser != null && existingUser.isVerified()) {
//			throw new RuntimeException("User Already Registered!");
//		}
		if (existingUser != null) {
			throw new RuntimeException("User Already Registered!");
		}

		// thực hiện lưu user trước, verify OTP tính sau, nếu verify OTP fail thì vẫn
		// lưu user nhưng login fail.
		Users users = Users.builder().userName(registerRequest.getUserName()).email(registerRequest.getEmail())
				.password(registerRequest.getPassword()).build();
		String otp = generateOTP();
		users.setOtp(otp);

		Users savedUser = usersRepository.save(users);
		sendVerificationEmail(savedUser.getEmail(), otp);

		// trả về username, email
		RegisterResponse response = RegisterResponse.builder().userName(users.getUserName()).email(users.getEmail())
				.build();
		return response;
	}

	private String generateOTP() {
		Random random = new Random();
		int otpValue = 100000 + random.nextInt(900000);
		return String.valueOf(otpValue);
	}

	private void sendVerificationEmail(String email, String otp) {
		String subject = "Email verification";
		String body = "Your verification otp is: " + otp;
		emailService.sendEmail(email, subject, body);
	}

	@Override
	public void verify(String email, String otp) {
		Users users = usersRepository.findByEmail(email);
		if (users == null) {
			throw new RuntimeException("User not found");
		} else if (users.isVerified()) {
			throw new RuntimeException("User is already verified");
		} else if (otp.equals(users.getOtp())) {
			users.setVerified(true);
			usersRepository.save(users);
		} else {
			throw new RuntimeException("OTP authentication failed.");
		}
	}

	@Override
	public void sendResetPasswordOtp(String email) {
		String otp = generateOTP();
		Users resetUser = usersRepository.findByEmail(email);
		if (resetUser == null) {
			throw new RuntimeException("Could not find the user with the email.");
		} else {
			resetUser.setOtp(otp);
			usersRepository.save(resetUser);
			String subject = "Verify your email to change the password.";
			String body = "Your OTP is: " + otp;
			emailService.sendEmail(email, subject, body);
		}
	}

	@Override
	public void resetPassword(String email, String newPassword, String otp) {
		Users resetUser = usersRepository.findByEmail(email);
		if (resetUser == null) {
			throw new RuntimeException("Cound not find the user");
		} else if (otp.equals(resetUser.getOtp())) {
			resetUser.setPassword(newPassword);
			usersRepository.save(resetUser);
		} else {
			throw new RuntimeException("Internal error");
		}
	}
}
