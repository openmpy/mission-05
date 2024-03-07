package com.example.mission05.domain.goods.controller;

import com.example.mission05.domain.goods.controller.docs.GoodsControllerDocs;
import com.example.mission05.domain.goods.dto.GoodsRequestDto.CreateGoodsRequestDto;
import com.example.mission05.domain.goods.dto.GoodsResponseDto.CreateGoodsResponseDto;
import com.example.mission05.domain.goods.dto.GoodsResponseDto.GetGoodsResponseDto;
import com.example.mission05.domain.goods.dto.GoodsResponseDto.SearchGoodsDto;
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
public class GoodsController implements GoodsControllerDocs {

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

    @GetMapping("/{goodsId}")
    public ResponseDto<GetGoodsResponseDto> getGoods(@PathVariable Long goodsId) {
        GetGoodsResponseDto responseDto = goodsService.getGoods(goodsId);
        return ResponseDto.success("선택한 상품 조회 기능", responseDto);
    }

    @GetMapping
    public ResponseDto<SearchGoodsDto> searchGoods(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String orderBy
    ) {
        SearchGoodsDto responseDto = goodsService.searchGoods(page, size, sortBy, orderBy);
        return ResponseDto.success("상품 목록 조회 기능", responseDto);
    }
}
