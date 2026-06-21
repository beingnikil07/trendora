package com.project.trendora.service;

import com.project.trendora.dto.LoginRequest;
import com.project.trendora.dto.UserRegisterRequest;
import com.project.trendora.dto.UserRegisterResponse;

public interface UserService {

     UserRegisterResponse register(UserRegisterRequest userRequest);
     String login(LoginRequest request);
}
