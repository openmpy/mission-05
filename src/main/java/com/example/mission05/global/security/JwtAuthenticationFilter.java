package com.example.mission05.global.security;

import com.example.mission05.domain.member.dto.MemberRequestDto.SigninMemberRequestDto;
import com.example.mission05.domain.member.dto.MemberResponseDto.SigninMemberResponseDto;
import com.example.mission05.domain.member.entity.type.AuthorityType;
import com.example.mission05.global.jwt.JwtUtil;
import com.example.mission05.global.util.CustomResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/v1/members/signin");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            SigninMemberRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), SigninMemberRequestDto.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.username(),
                            requestDto.password(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        AuthorityType role = ((UserDetailsImpl) authResult.getPrincipal()).member().getAuthority();

        String accessToken = jwtUtil.createAccessToken(username, role);
        String refreshToken = jwtUtil.createRefreshToken(username);

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);
        jwtUtil.addJwtToCookie(refreshToken, response);

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        SigninMemberResponseDto responseDto = new SigninMemberResponseDto(userDetails.member());
        CustomResponseUtil.success(response, responseDto);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        CustomResponseUtil.fail(response, "로그인 실패", HttpStatus.FORBIDDEN);
    }
}
