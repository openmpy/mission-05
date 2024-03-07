package com.example.mission05.domain.member.dto;

import com.example.mission05.domain.member.entity.Member;

import java.time.LocalDateTime;

public class MemberResponseDto {

    public record SignupMemberResponseDto(
            String email,
            LocalDateTime createdAt
    ) {
        public SignupMemberResponseDto(Member member) {
            this(member.getEmail(), member.getCreatedAt());
        }
    }
}
