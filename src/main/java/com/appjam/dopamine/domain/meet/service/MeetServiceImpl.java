package com.appjam.dopamine.domain.meet.service;

import com.appjam.dopamine.domain.meet.repository.MeetRepository;
import com.appjam.dopamine.domain.user.entity.User;
import com.appjam.dopamine.domain.user.repository.UserRepository;
import com.appjam.dopamine.domain.user.request.dto.MeetDTO;
import com.appjam.dopamine.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetServiceImpl implements MeetService {
    private final MeetRepository meetRepository;
    private final UserRepository userRepository;

    @Override
    public BaseResponse meetCount(Authentication authentication, MeetDTO meetDTO) {
        User user = userRepository.findByEmail(authentication.getName()).get();
        user.setMeetCount(meetDTO.getMeetCount());
        meetRepository.save(user);
        return new BaseResponse(HttpStatus.OK, "만남 횟수 저장 성공");
    }
}