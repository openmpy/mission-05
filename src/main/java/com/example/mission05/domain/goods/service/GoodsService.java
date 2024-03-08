package com.example.mission05.domain.goods.service;

import com.example.mission05.domain.goods.dto.GoodsRequestDto.CreateGoodsRequestDto;
import com.example.mission05.domain.goods.dto.GoodsResponseDto.CreateGoodsResponseDto;
import com.example.mission05.domain.goods.dto.GoodsResponseDto.GetGoodsResponseDto;
import com.example.mission05.domain.goods.dto.GoodsResponseDto.SearchGoodsDto;
import com.example.mission05.domain.goods.entity.Goods;
import com.example.mission05.domain.goods.repository.GoodsRepository;
import com.example.mission05.domain.goods.repository.GoodsSearchRepository;
import com.example.mission05.domain.member.repository.MemberRepository;
import com.example.mission05.global.exception.CustomApiException;
import com.example.mission05.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GoodsService {

    private final GoodsRepository goodsRepository;
    private final GoodsSearchRepository goodsSearchRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CreateGoodsResponseDto createGoods(String email, CreateGoodsRequestDto requestDto) {
        if (!memberRepository.existsByEmail(email)) {
            throw new CustomApiException(ErrorCode.NOT_FOUND_EMAIL.getMessage());
        }

        Goods goods = goodsRepository.save(requestDto.toEntity());
        return new CreateGoodsResponseDto(goods);
    }

    @Transactional(readOnly = true)
    public GetGoodsResponseDto getGoods(Long goodsId) {
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_GOODS.getMessage())
        );

        return new GetGoodsResponseDto(goods);
    }

    @Transactional(readOnly = true)
    public SearchGoodsDto searchGoods(int page, int size, String sortBy, String orderBy) {
        List<GetGoodsResponseDto> responseDtoList = goodsSearchRepository.searchGoods(page, size, sortBy, orderBy).stream()
                .map(GetGoodsResponseDto::new)
                .toList();

        return new SearchGoodsDto(responseDtoList, page + 1, responseDtoList.size());
    }
}
