package com.example.mission05.domain.member.controller;

import com.example.mission05.domain.member.dto.MemberRequestDto.SignupMemberRequestDto;
import com.example.mission05.domain.member.dto.MemberResponseDto.SignupMemberResponseDto;
import com.example.mission05.domain.member.service.MemberService;
import com.example.mission05.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<SignupMemberResponseDto> createMember(@RequestBody @Valid SignupMemberRequestDto requestDto) {
        SignupMemberResponseDto result = memberService.createMember(requestDto);
        return ResponseDto.success("회원가입 기능", result);
    }
}
