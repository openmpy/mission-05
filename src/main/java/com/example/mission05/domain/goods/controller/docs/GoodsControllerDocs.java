package com.example.mission05.domain.goods.controller.docs;

import com.example.mission05.domain.goods.dto.GoodsRequestDto.CreateGoodsRequestDto;
import com.example.mission05.domain.goods.dto.GoodsResponseDto;
import com.example.mission05.domain.goods.dto.GoodsResponseDto.CreateGoodsResponseDto;
import com.example.mission05.global.dto.ResponseDto;
import com.example.mission05.global.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Goods", description = "상품 관련 API")
public interface GoodsControllerDocs {

    @Operation(summary = "상품 등록 기능", description = "상품을 등록할 수 있는 API")
    ResponseDto<CreateGoodsResponseDto> createGoods(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid CreateGoodsRequestDto requestDto
    );

    @Operation(summary = "선택한 상품 조회 기능", description = "선택한 상품을 조회할 수 있는 API")
    ResponseDto<GoodsResponseDto.GetGoodsResponseDto> getGoods(@PathVariable Long goodsId);

    @Operation(summary = "상품 목록 조회 기능", description = "상품 목록을 조회할 수 있는 API")
    ResponseDto<GoodsResponseDto.SearchGoodsDto> searchGoods(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String orderBy
    );

    @Operation(summary = "상품 이미지 업로드 기능", description = "상품 이미지를 업로드할 수 있는 API")
    ResponseDto<GoodsResponseDto.GetGoodsResponseDto> uploadGoodsImage(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long goodsId,
            @RequestParam("file") MultipartFile file
    );
}
