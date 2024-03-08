package com.example.mission05.domain.member.service;

import com.example.mission05.domain.member.dto.MemberRequestDto.SignupMemberRequestDto;
import com.example.mission05.domain.member.dto.MemberResponseDto.SignupMemberResponseDto;
import com.example.mission05.domain.member.entity.Member;
import com.example.mission05.domain.member.repository.MemberRepository;
import com.example.mission05.global.exception.CustomApiException;
import com.example.mission05.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignupMemberResponseDto createMember(SignupMemberRequestDto requestDto) {
        if (memberRepository.existsByEmail(requestDto.email())) {
            throw new CustomApiException(ErrorCode.ALREADY_EXIST_EMAIL.getMessage());
        }
        if (memberRepository.existsByPhone(requestDto.phone())) {
            throw new CustomApiException(ErrorCode.ALREADY_EXIST_EMAIL.getMessage());
        }

        String encodedPassword = passwordEncoder.encode(requestDto.password());
        Member member = memberRepository.save(requestDto.toEntity(encodedPassword));
        return new SignupMemberResponseDto(member);
    }
}
