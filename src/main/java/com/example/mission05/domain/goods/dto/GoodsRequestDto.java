package com.example.mission05.domain.goods.dto;

import com.example.mission05.domain.goods.entity.Goods;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class GoodsRequestDto {

    public record CreateGoodsRequestDto(
            @NotBlank(message = "상품명을 입력해주세요.")
            String name,

            @Positive(message = "가격을 입력해주세요.")
            Integer price,

            @PositiveOrZero(message = "수량을 입력해주세요.")
            Integer amount,

            @NotBlank(message = "소개를 입력해주세요.")
            String introduction,

            @NotBlank(message = "카테고리를 입력해주세요.")
            String category
    ) {
        public Goods toEntity() {
            return Goods.builder()
                    .name(name)
                    .price(price)
                    .amount(amount)
                    .introduction(introduction)
                    .category(category)
                    .build();
        }
    }
}
