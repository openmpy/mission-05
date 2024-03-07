package com.example.mission05.domain.goods.dto;

import com.example.mission05.domain.goods.entity.Goods;

import java.time.LocalDateTime;
import java.util.List;

public class GoodsResponseDto {

    public record CreateGoodsResponseDto(
            String name,
            Integer price,
            Integer amount,
            String introduction,
            String category,
            String imageUrl,
            LocalDateTime createdAt
    ) {
        public CreateGoodsResponseDto(Goods goods) {
            this(
                    goods.getName(),
                    goods.getPrice(),
                    goods.getAmount(),
                    goods.getIntroduction(),
                    goods.getCategory(),
                    goods.getImageUrl(),
                    goods.getCreatedAt()
            );
        }
    }

    public record GetGoodsResponseDto(
            String name,
            Integer price,
            Integer amount,
            String introduction,
            String category,
            String imageUrl,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) {
        public GetGoodsResponseDto(Goods goods) {
            this(
                    goods.getName(),
                    goods.getPrice(),
                    goods.getAmount(),
                    goods.getIntroduction(),
                    goods.getCategory(),
                    goods.getImageUrl(),
                    goods.getCreatedAt(),
                    goods.getModifiedAt()
            );
        }
    }

    public record SearchGoodsDto(
            List<GetGoodsResponseDto> goodsList,
            Integer page,
            Integer count
    ) {
    }

    public record BuyGoodsResponseDto(
            String goodsName,
            Integer amount,
            int totalPrice
    ) {
    }
}
