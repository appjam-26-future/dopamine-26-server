package com.appjam.dopamine.domain.exercise.controller;

import com.appjam.dopamine.domain.exercise.service.ExerciseService;
import com.appjam.dopamine.domain.user.request.dto.ExerciseDTO;
import com.appjam.dopamine.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
@Tag(name = "운동", description = "운동 관련 api 입니다.")
public class ExerciseController {
    private final ExerciseService exerciseService;
    @PostMapping("/count")
    @Operation(summary = "운동 횟수", description = "운동 횟수를 저장합니다.")
    public BaseResponse exerciseCount(
            Authentication authentication,
            @RequestBody ExerciseDTO exerciseDTO
    ){
        return exerciseService.exerciseCount(authentication, exerciseDTO);
    }
}