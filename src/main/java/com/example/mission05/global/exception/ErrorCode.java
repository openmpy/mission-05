package com.example.mission05.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다."),
    NOT_VALID_JWT_TOKEN("유효하지 않는 토큰입니다."),
    ALREADY_EXIST_EMAIL("이미 가입된 이메일입니다."),
    ALREADY_PHONE_EMAIL("이미 가입된 전화번호입니다."),
    NOT_FOUND_EMAIL("찾을 수 없는 이메일입니다."),
    NOT_FOUND_GOODS("찾을 수 없는 상품입니다."),
    NOT_FOUND_BASKET("찾을 수 없는 장바구니입니다."),
    LACK_AMOUNT_GOODS("상품 수량이 부족합니다."),
    NOT_INCORRECT_MEMBER("잘못된 회원 정보입니다."),
    NOT_VALID_ACCESS_TOKEN("찾을 수 없는 액세스 토큰입니다."),
    NOT_VALID_REFRESH_TOKEN("찾을 수 없는 리프레쉬 토큰입니다."),
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
