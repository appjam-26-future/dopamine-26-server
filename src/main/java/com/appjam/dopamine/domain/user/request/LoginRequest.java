package com.appjam.dopamine.domain.user.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRequest {
    private String email;
    private String password;
}
