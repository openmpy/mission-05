package com.example.mission05.domain.member.dto;

import com.example.mission05.domain.member.entity.Member;
import com.example.mission05.domain.member.entity.type.AuthorityType;
import com.example.mission05.domain.member.entity.type.GenderType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class MemberRequestDto {

    public record SignupMemberRequestDto(
            @NotBlank(message = "이메일을 입력해주세요.")
            @Email(message = "올바른 이메일 형식이 아닙니다.")
            String email,

            @Pattern(
                    message = "최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자로 구성되어야 합니다.",
                    regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,15}$"
            )
            String password,

            GenderType gender,

            @NotBlank(message = "전화번호를 입력해주세요.")
            String phone,

            @NotBlank(message = "주소를 입력해주세요.")
            String address,

            AuthorityType authority
    ) {
        public Member toEntity(String encodedPassword) {
            return Member.builder()
                    .email(email)
                    .password(encodedPassword)
                    .gender(gender)
                    .phone(phone)
                    .address(address)
                    .authority(authority)
                    .build();
        }
    }
}
