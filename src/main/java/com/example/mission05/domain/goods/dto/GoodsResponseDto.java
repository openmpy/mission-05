package com.example.mission05.domain.goods.dto;

import com.example.mission05.domain.goods.entity.Goods;

import java.time.LocalDateTime;

public class GoodsResponseDto {

    public record CreateGoodsResponseDto(
            String name,
            Integer price,
            Integer amount,
            String introduction,
            String category,
            LocalDateTime createdAt
    ) {
        public CreateGoodsResponseDto(Goods goods) {
            this(goods.getName(), goods.getPrice(), goods.getAmount(), goods.getIntroduction(), goods.getCategory(), goods.getCreatedAt());
        }
    }
}
