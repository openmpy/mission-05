package com.example.mission05.domain.basket.controller;

import com.example.mission05.domain.basket.dto.BasketResponseDto.GetBasketListResponseDto;
import com.example.mission05.domain.basket.service.BasketService;
import com.example.mission05.domain.goods.dto.GoodsRequestDto.BuyGoodsRequestDto;
import com.example.mission05.domain.goods.dto.GoodsResponseDto.BuyGoodsResponseDto;
import com.example.mission05.global.dto.ResponseDto;
import com.example.mission05.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class BasketController {

    private final BasketService basketService;

    @PostMapping("/goods/{goodsId}/buy")
    public ResponseDto<BuyGoodsResponseDto> buyGoods(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long goodsId,
            @RequestBody @Valid BuyGoodsRequestDto requestDto
    ) {
        BuyGoodsResponseDto responseDto = basketService.buyGoods(userDetails.getUsername(), goodsId, requestDto);
        return ResponseDto.success("장바구니 추가 기능", responseDto);
    }

    @GetMapping
    public ResponseDto<GetBasketListResponseDto> getBasketList(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        GetBasketListResponseDto result = basketService.getBasketList(userDetails.getUsername());
        return ResponseDto.success("장바구니 조회 기능", result);
    }
}
