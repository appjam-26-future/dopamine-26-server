package com.appjam.dopamine.domain.user.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtResponse {
    private final String type = "Bearer";
    private String token;
    private Long id;
    private String email;
    private String name;

    public static JwtResponse setJwtResponse(String token, Long id, String email, String name) {
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.token = token;
        jwtResponse.id = id;
        jwtResponse.email = email;
        jwtResponse.name = name;
        return jwtResponse;
    }
}