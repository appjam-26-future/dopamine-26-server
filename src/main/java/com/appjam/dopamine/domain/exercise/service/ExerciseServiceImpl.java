package com.appjam.dopamine.domain.exercise.service;

import com.appjam.dopamine.domain.exercise.repository.ExerciseRepository;
import com.appjam.dopamine.domain.user.entity.User;
import com.appjam.dopamine.domain.user.repository.UserRepository;
import com.appjam.dopamine.domain.user.request.dto.ExerciseDTO;
import com.appjam.dopamine.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;

    @Override
    public BaseResponse exerciseCount(Authentication authentication, ExerciseDTO exerciseDTO) {
        User exercise = userRepository.findByEmail(authentication.getName()).get();
        exercise.setExerciseCount(exerciseDTO.getExerciseCount());
        exerciseRepository.save(exercise);
        return new BaseResponse(HttpStatus.OK, "운동 횟수 저장 성공");
    }
}