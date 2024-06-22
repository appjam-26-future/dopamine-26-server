package com.appjam.dopamine.domain.exercise.service;

import com.appjam.dopamine.domain.user.request.dto.ExerciseDTO;
import com.appjam.dopamine.global.common.BaseResponse;
import org.springframework.security.core.Authentication;

public interface ExerciseService {
    BaseResponse exerciseCount(Authentication authentication, ExerciseDTO exerciseDTO);
}
