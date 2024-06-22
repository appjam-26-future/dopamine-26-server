package com.appjam.dopamine.global.exception.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements ErrorProperty {
    EMAIL_BAD_REQUEST(HttpStatus.BAD_REQUEST, "사용중인 이메일 입니다.");

    private final HttpStatus status;
    private final String message;
}