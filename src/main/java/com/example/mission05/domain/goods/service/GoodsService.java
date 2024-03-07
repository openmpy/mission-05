package com.example.mission05.domain.goods.service;

import com.example.mission05.domain.goods.dto.GoodsRequestDto.CreateGoodsRequestDto;
import com.example.mission05.domain.goods.dto.GoodsResponseDto.CreateGoodsResponseDto;
import com.example.mission05.domain.goods.entity.Goods;
import com.example.mission05.domain.goods.repository.GoodsRepository;
import com.example.mission05.domain.member.repository.MemberRepository;
import com.example.mission05.global.exception.CustomApiException;
import com.example.mission05.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GoodsService {

    private final GoodsRepository goodsRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CreateGoodsResponseDto createGoods(String email, CreateGoodsRequestDto requestDto) {
        if (!memberRepository.existsByEmail(email)) {
            throw new CustomApiException(ErrorCode.NOT_FOUND_EMAIL.getMessage());
        }

        Goods goods = goodsRepository.save(requestDto.toEntity());
        return new CreateGoodsResponseDto(goods);
    }
}
