package com.appjam.dopamine.domain.user.presentation;

import com.appjam.dopamine.domain.user.service.UserService;
import com.appjam.dopamine.domain.user.request.LoginRequest;
import com.appjam.dopamine.domain.user.request.SignupRequest;
import com.appjam.dopamine.domain.user.response.ApiResponse;
import com.appjam.dopamine.domain.user.response.JwtResponse;
import com.appjam.dopamine.domain.user.service.UserServiceImpl;
import com.appjam.dopamine.global.exception.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "유저", description = "유저 관련 api 입니다.")
public class UserController {
    private final UserService userService;

    private final UserServiceImpl userServiceImpl;

    @Operation(summary = "로그인", description = "로그인을 진행합니다.")
    @PostMapping("/signin")

    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(userServiceImpl.authenticateAndGenerateJWT(loginRequest.getEmail(), loginRequest.getPassword()));
    }

    @Operation(summary = "회원가입", description = "회원가입을 진행합니다.")
    @PostMapping("/signup")
    public ResponseEntity<?> registerAndAuthenticateUser(@RequestBody SignupRequest signupRequest) throws BusinessException {

        /* 유저 등록 */
        userService.registerUser(signupRequest);

        JwtResponse jwtResponse = userServiceImpl.authenticateAndGenerateJWT(signupRequest.getEmail(), signupRequest.getPassword());
        ApiResponse<JwtResponse> response = ApiResponse.setApiResponse(true, "회원 가입이 완료 되었습니다!", jwtResponse);

        return ResponseEntity.ok().body(response);
    }
}
