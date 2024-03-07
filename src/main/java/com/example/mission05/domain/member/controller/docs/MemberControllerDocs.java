package com.example.mission05.domain.member.controller.docs;

import com.example.mission05.domain.member.dto.MemberRequestDto.SignupMemberRequestDto;
import com.example.mission05.domain.member.dto.MemberResponseDto.SignupMemberResponseDto;
import com.example.mission05.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Members", description = "회원가입 관련 API")
public interface MemberControllerDocs {

    @Operation(summary = "회원가입 기능", description = "회원 가입을 할 수 있는 API")
    ResponseDto<SignupMemberResponseDto> createMember(@RequestBody @Valid SignupMemberRequestDto requestDto);
}
