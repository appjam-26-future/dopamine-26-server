package com.appjam.dopamine.domain.meet.service;

import com.appjam.dopamine.domain.user.request.dto.MeetDTO;
import com.appjam.dopamine.global.common.BaseResponse;
import org.springframework.security.core.Authentication;

public interface MeetService {
    BaseResponse meetCount(Authentication authentication, MeetDTO meetDTO);
}
