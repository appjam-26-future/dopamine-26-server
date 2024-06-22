package com.appjam.dopamine.domain.meet.controller;

import com.appjam.dopamine.domain.meet.service.MeetService;
import com.appjam.dopamine.domain.user.request.dto.MeetDTO;
import com.appjam.dopamine.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
@Tag(name = "사람 만나는 횟수", description = "만남 관련 api 입니다.")
public class MeetController {
    private final MeetService meetService;

    @PostMapping("/count")
    @Operation(summary = "살함 만나는 횟수", description = "만남 횟수를 저장합니다.")
    public BaseResponse meetCount(
            Authentication authentication,
            @RequestBody MeetDTO meetDTO){
        return meetService.meetCount(authentication, meetDTO);
    }
}