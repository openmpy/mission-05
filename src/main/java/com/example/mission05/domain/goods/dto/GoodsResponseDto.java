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
            LocalDateTime createdAt
    ) {
        public CreateGoodsResponseDto(Goods goods) {
            this(
                    goods.getName(),
                    goods.getPrice(),
                    goods.getAmount(),
                    goods.getIntroduction(),
                    goods.getCategory(),
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
        public SearchGoodsDto(List<GetGoodsResponseDto> goodsList, Integer page, Integer count) {
            this.goodsList = goodsList;
            this.page = page;
            this.count = count;
        }
    }

    public record BuyGoodsResponseDto(
            String goodsName,
            Integer amount,
            int totalPrice
    ) {
        public BuyGoodsResponseDto(String goodsName, Integer amount, int totalPrice) {
            this.goodsName = goodsName;
            this.amount = amount;
            this.totalPrice = totalPrice;
        }
    }
}
