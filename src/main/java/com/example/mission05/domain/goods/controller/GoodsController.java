package com.example.mission05.domain.goods.controller;

import com.example.mission05.domain.goods.dto.GoodsRequestDto.CreateGoodsRequestDto;
import com.example.mission05.domain.goods.dto.GoodsResponseDto.CreateGoodsResponseDto;
import com.example.mission05.domain.goods.service.GoodsService;
import com.example.mission05.domain.member.entity.type.AuthorityType.Authority;
import com.example.mission05.global.dto.ResponseDto;
import com.example.mission05.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/goods")
@RestController
public class GoodsController {

    private final GoodsService goodsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Secured(Authority.ADMIN)
    public ResponseDto<CreateGoodsResponseDto> createGoods(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid CreateGoodsRequestDto requestDto
    ) {
        CreateGoodsResponseDto responseDto = goodsService.createGoods(userDetails.getUsername(), requestDto);
        return ResponseDto.success("상품 등록 기능", responseDto);
    }
}
