package com.example.mission05.domain.basket.controller;

import com.example.mission05.domain.basket.controller.docs.BasketControllerDocs;
import com.example.mission05.domain.basket.dto.BasketRequestDto.EditBasketRequestDto;
import com.example.mission05.domain.basket.dto.BasketResponseDto.GetBasketListResponseDto;
import com.example.mission05.domain.basket.dto.BasketResponseDto.GetBasketResponseDto;
import com.example.mission05.domain.basket.service.BasketService;
import com.example.mission05.domain.goods.dto.GoodsRequestDto.BuyGoodsRequestDto;
import com.example.mission05.domain.goods.dto.GoodsResponseDto.BuyGoodsResponseDto;
import com.example.mission05.global.dto.ResponseDto;
import com.example.mission05.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class BasketController implements BasketControllerDocs {

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

    @GetMapping("/baskets")
    public ResponseDto<GetBasketListResponseDto> getBasketList(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        GetBasketListResponseDto result = basketService.getBasketList(userDetails.getUsername());
        return ResponseDto.success("장바구니 조회 기능", result);
    }

    @PatchMapping("/baskets/{basketId}")
    public ResponseDto<GetBasketResponseDto> editBasket(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long basketId,
            @RequestBody @Valid EditBasketRequestDto requestDto
    ) {
        GetBasketResponseDto responseDto = basketService.editBasket(userDetails.getUsername(), basketId, requestDto);
        return ResponseDto.success("장바구니 수정 기능", responseDto);
    }

    @DeleteMapping("/baskets/{basketId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseDto<Void> deleteBasket(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long basketId
    ) {
        basketService.deleteBasket(userDetails.getUsername(), basketId);
        return ResponseDto.success("장바구니 삭제 기능", null);
    }
}
