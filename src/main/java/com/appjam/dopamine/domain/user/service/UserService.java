package com.appjam.dopamine.domain.user.service;


import com.appjam.dopamine.domain.user.request.SignupRequest;
import com.appjam.dopamine.global.exception.BusinessException;

public interface UserService {
    void registerUser(SignupRequest signupRequest) throws BusinessException;
}