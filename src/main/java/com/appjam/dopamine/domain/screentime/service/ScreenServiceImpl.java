package com.appjam.dopamine.domain.screentime.service;

import com.appjam.dopamine.domain.screentime.repository.ScreenRepository;
import com.appjam.dopamine.domain.user.entity.User;
import com.appjam.dopamine.domain.user.repository.UserRepository;
import com.appjam.dopamine.domain.user.request.dto.ScreenDTO;
import com.appjam.dopamine.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScreenServiceImpl implements ScreenService {
    private final ScreenRepository screenRepository;
    private final UserRepository userRepository;

    @Override
    public BaseResponse screenTimeAvg(Authentication authentication, ScreenDTO screenDTO) {
        User user = userRepository.findByEmail(authentication.getName()).get();
        user.setExerciseCount(screenDTO.getScreenTimeAvg());
        screenRepository.save(user);
        return new BaseResponse(HttpStatus.OK, "스크린타임 평균 저장 성공");
    }
}