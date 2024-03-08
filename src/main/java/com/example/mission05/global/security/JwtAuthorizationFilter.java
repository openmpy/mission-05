package com.example.mission05.global.security;

import com.example.mission05.domain.member.entity.Member;
import com.example.mission05.global.exception.CustomJwtException;
import com.example.mission05.global.exception.ErrorCode;
import com.example.mission05.global.jwt.JwtCode;
import com.example.mission05.global.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j(topic = "JwtAuthorizationFilter")
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/api/v1/members/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = jwtUtil.getAccessTokenFromHeader(request);
        String refreshToken = jwtUtil.getTokenFromRequest(request);

        if (StringUtils.hasText(accessToken) && StringUtils.hasText(refreshToken)) {
            if (jwtUtil.validateToken(accessToken) == JwtCode.DENIED) {
                throw new CustomJwtException(ErrorCode.NOT_VALID_JWT_TOKEN.getMessage());
            } else if (jwtUtil.validateToken(accessToken) == JwtCode.EXPIRED) {
                Member member = jwtUtil.getMemberFromRefreshToken(refreshToken);
                String newAccessToken = jwtUtil.createAccessToken(member.getEmail(), member.getAuthority());
                response.addHeader(JwtUtil.AUTHORIZATION_HEADER, newAccessToken);
                accessToken = newAccessToken.substring(7);
            }

            Claims info = jwtUtil.getUserInfoFromToken(accessToken);

            try {
                setAuthentication(info.getSubject());
            } catch (Exception e) {
                throw new CustomJwtException(e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }

    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(username);
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    private Authentication createAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
