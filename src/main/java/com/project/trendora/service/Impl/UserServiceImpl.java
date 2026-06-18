package com.project.trendora.service.Impl;
import com.project.trendora.Repository.UserRepository;
import com.project.trendora.dto.UserRegisterRequest;
import com.project.trendora.dto.UserRegisterResponse;
import com.project.trendora.exceptions.UserAlreadyExists;
import com.project.trendora.models.User;
import com.project.trendora.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest userRequest) {
        if (userRepository.existsByUserName(userRequest.getUserName())){
            throw new UserAlreadyExists("user is already exist");
        }
        User user=new User();
        user.setEmail(userRequest.getEmail());
        user.setUserName(userRequest.getUserName());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPassword(userRequest.getPassword());
        user.setRoles("USER");

        User savedUser=userRepository.save(user);

        return mapToResponse(savedUser);
    }

    private UserRegisterResponse mapToResponse(User savedUser){
        UserRegisterResponse response=new UserRegisterResponse();
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setUserName(savedUser.getUserName());
        response.setEmail(savedUser.getEmail());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());
        return response;

    }



}
