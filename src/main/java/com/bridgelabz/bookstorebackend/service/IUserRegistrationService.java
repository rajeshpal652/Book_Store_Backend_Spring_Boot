package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.LoginDTO;
import com.bridgelabz.bookstorebackend.dto.ResponseDTO;
import com.bridgelabz.bookstorebackend.dto.UserRegistrationDTO;
import com.bridgelabz.bookstorebackend.model.UserRegistrationData;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserRegistrationService {
    public ResponseDTO createUserData(UserRegistrationDTO userDTO);

    public List<UserRegistrationData> getAllUsers();

    public ResponseDTO updateUserData(int userId, UserRegistrationDTO userDTO);

    public ResponseDTO deleteUserData(int userId);

    public UserRegistrationData getUserDataById(int userId);

    public ResponseDTO loginValidation(LoginDTO loginDTO);

    public Boolean verifyUser(String token);

    UserRegistrationData forgotPassword(String emailId, String password);

}
