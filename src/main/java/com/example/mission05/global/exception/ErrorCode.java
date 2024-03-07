package com.example.mission05.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다."),
    NOT_VALID_JWT_TOKEN("유효하지 않는 토큰입니다."),
    ALREADY_EXIST_EMAIL("이미 가입된 이메일입니다."),
    ALREADY_PHONE_EMAIL("이미 가입된 전화번호입니다."),
    NOT_FOUND_EMAIL("찾을 수 없는 이메일입니다."),
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
