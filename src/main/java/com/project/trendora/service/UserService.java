package com.project.trendora.service;

import com.project.trendora.dto.UserRegisterRequest;
import com.project.trendora.dto.UserRegisterResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {

     UserRegisterResponse register(UserRegisterRequest userRequest);

}
