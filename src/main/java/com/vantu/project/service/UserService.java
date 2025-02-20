package com.vantu.project.service;

import com.vantu.project.model.Users;
import com.vantu.project.requests.RegisterRequest;
import com.vantu.project.responses.RegisterResponse;

public interface UserService {

    RegisterResponse register(RegisterRequest registerRequest);

    void verify(String email,String otp);
    
	Users login(String email, String password);
	
	void sendResetPasswordOtp(String email);
	
	void resetPassword(String email, String newPassword, String otp);
}
