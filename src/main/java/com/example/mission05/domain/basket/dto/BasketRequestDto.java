package com.example.mission05.domain.basket.dto;

import jakarta.validation.constraints.NotNull;

public class BasketRequestDto {

    public record EditBasketRequestDto(
            @NotNull(message = "수량을 입력해주세요.")
            Integer amount
    ) {

    }
}
