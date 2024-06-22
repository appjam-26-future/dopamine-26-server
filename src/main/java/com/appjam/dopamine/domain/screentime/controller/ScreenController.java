package com.appjam.dopamine.domain.screentime.controller;

import com.appjam.dopamine.domain.screentime.service.ScreenService;
import com.appjam.dopamine.domain.user.request.dto.ScreenDTO;
import com.appjam.dopamine.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/screen")
@RequiredArgsConstructor
@Tag(name = "스크린 타임", description = "스크린 타임 관련 api 입니다.")
public class ScreenController {
    private final ScreenService screenService;

    @PostMapping("/avg")
    @Operation(summary = "스크린 타임 평균", description = "운동 횟수를 저장합니다.")
    public BaseResponse screenTimeAvg(
            Authentication authentication,
            @RequestBody ScreenDTO screenDTO){
        return screenService.screenTimeAvg(authentication, screenDTO);
    }
}