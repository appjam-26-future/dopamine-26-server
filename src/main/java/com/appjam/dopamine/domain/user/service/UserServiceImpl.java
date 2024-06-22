package com.appjam.dopamine.domain.user.service;

import com.appjam.dopamine.domain.user.entity.User;
import com.appjam.dopamine.domain.user.repository.UserRepository;
import com.appjam.dopamine.domain.user.request.SignupRequest;
import com.appjam.dopamine.domain.user.response.JwtResponse;
import com.appjam.dopamine.domain.user.security.jwt.JwtUtils;
import com.appjam.dopamine.domain.user.security.service.UserDetailsImpl;
import com.appjam.dopamine.global.exception.BusinessException;
import com.appjam.dopamine.global.exception.error.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    @Transactional
    public void registerUser(SignupRequest signupRequest) throws BusinessException {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new BusinessException(
                    ErrorCode.EMAIL_BAD_REQUEST);
        }
        User user = User.registerUser(
                signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()),
                signupRequest.getName()
        );
        userRepository.save(user);
    }

    /* 인증 및 JWT 토큰 생성 */
    public JwtResponse authenticateAndGenerateJWT(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return JwtResponse.setJwtResponse(jwt, userDetails.getId(), userDetails.getEmail(), userDetails.getName());
    }
}
