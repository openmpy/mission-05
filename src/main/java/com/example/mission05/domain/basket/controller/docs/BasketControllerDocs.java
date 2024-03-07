package com.example.mission05.domain.basket.controller.docs;

import com.example.mission05.domain.basket.dto.BasketRequestDto.EditBasketRequestDto;
import com.example.mission05.domain.basket.dto.BasketResponseDto.GetBasketListResponseDto;
import com.example.mission05.domain.basket.dto.BasketResponseDto.GetBasketResponseDto;
import com.example.mission05.domain.goods.dto.GoodsRequestDto.BuyGoodsRequestDto;
import com.example.mission05.domain.goods.dto.GoodsResponseDto.BuyGoodsResponseDto;
import com.example.mission05.global.dto.ResponseDto;
import com.example.mission05.global.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Baskets", description = "장바구니 관련 API")
public interface BasketControllerDocs {

    @Operation(summary = "장바구니 추가 기능", description = "장바구니를 추가할 수 있는 API")
    ResponseDto<BuyGoodsResponseDto> buyGoods(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long goodsId,
            @RequestBody @Valid BuyGoodsRequestDto requestDto
    );

    @Operation(summary = "장바구니 조회 기능", description = "장바구니를 조회할 수 있는 API")
    ResponseDto<GetBasketListResponseDto> getBasketList(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );

    @Operation(summary = "장바구니 수정 기능", description = "장바구니를 수정할 수 있는 API")
    ResponseDto<GetBasketResponseDto> editBasket(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long basketId,
            @RequestBody @Valid EditBasketRequestDto requestDto
    );

    @Operation(summary = "장바구니 삭제 기능", description = "장바구니를 삭제할 수 있는 API")
    ResponseDto<Void> deleteBasket(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long basketId
    );
}
