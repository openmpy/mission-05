package com.example.mission05.global.security;

import com.example.mission05.domain.member.entity.Member;
import com.example.mission05.domain.member.repository.MemberRepository;
import com.example.mission05.global.exception.CustomApiException;
import com.example.mission05.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_EMAIL.getMessage())
        );
        return new UserDetailsImpl(member);
    }
}
