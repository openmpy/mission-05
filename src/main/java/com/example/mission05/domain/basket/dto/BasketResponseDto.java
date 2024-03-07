package com.example.mission05.domain.basket.dto;

import java.util.List;

public class BasketResponseDto {

    public record GetBasketResponseDto(
            String name,
            Integer price,
            Integer amount
    ) {
        public GetBasketResponseDto(String name, Integer price, Integer amount) {
            this.name = name;
            this.price = price;
            this.amount = amount;
        }
    }

    public record GetBasketListResponseDto(
            List<GetBasketResponseDto> goods,
            Long totalPrice
    ) {

    }
}
