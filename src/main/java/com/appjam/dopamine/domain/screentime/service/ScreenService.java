package com.appjam.dopamine.domain.screentime.service;

import com.appjam.dopamine.domain.user.request.dto.ExerciseDTO;
import com.appjam.dopamine.domain.user.request.dto.ScreenDTO;
import com.appjam.dopamine.global.common.BaseResponse;
import org.springframework.security.core.Authentication;

public interface ScreenService {
    BaseResponse screenTimeAvg(Authentication authentication, ScreenDTO screenDTO);
}
