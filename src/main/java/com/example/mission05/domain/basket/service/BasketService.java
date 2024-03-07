package com.example.mission05.domain.basket.service;

import com.example.mission05.domain.basket.dto.BasketRequestDto.EditBasketRequestDto;
import com.example.mission05.domain.basket.dto.BasketResponseDto.GetBasketListResponseDto;
import com.example.mission05.domain.basket.dto.BasketResponseDto.GetBasketResponseDto;
import com.example.mission05.domain.basket.entity.Basket;
import com.example.mission05.domain.basket.repository.BasketRepository;
import com.example.mission05.domain.goods.dto.GoodsRequestDto.BuyGoodsRequestDto;
import com.example.mission05.domain.goods.dto.GoodsResponseDto.BuyGoodsResponseDto;
import com.example.mission05.domain.goods.entity.Goods;
import com.example.mission05.domain.goods.repository.GoodsRepository;
import com.example.mission05.domain.member.entity.Member;
import com.example.mission05.domain.member.repository.MemberRepository;
import com.example.mission05.global.exception.CustomApiException;
import com.example.mission05.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final MemberRepository memberRepository;
    private final GoodsRepository goodsRepository;

    @Transactional
    public BuyGoodsResponseDto buyGoods(String email, Long goodsId, BuyGoodsRequestDto requestDto) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_EMAIL.getMessage())
        );
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_GOODS.getMessage())
        );
        if (goods.getAmount() < requestDto.amount()) {
            throw new CustomApiException(ErrorCode.LACK_AMOUNT_GOODS.getMessage());
        }

        // 물건 수량 감소
        goods.updateAmount(goods.getAmount() - requestDto.amount());

        return basketRepository.findByMemberAndGoods(member, goods)
                .map(basket -> {
                    basket.updateAmount(basket.getAmount() + requestDto.amount());
                    return new BuyGoodsResponseDto(goods.getName(), basket.getAmount(), goods.getPrice() * basket.getAmount());
                })
                .orElseGet(() -> {
                    Basket basket = Basket.builder()
                            .member(member)
                            .goods(goods)
                            .amount(requestDto.amount())
                            .build();

                    Basket saved = basketRepository.save(basket);
                    return new BuyGoodsResponseDto(goods.getName(), saved.getAmount(), goods.getPrice() * saved.getAmount());
                });
    }

    @Transactional(readOnly = true)
    public GetBasketListResponseDto getBasketList(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_EMAIL.getMessage())
        );

        List<Basket> basketList = basketRepository.findByMemberOrderByCreatedAtDesc(member);

        List<GetBasketResponseDto> basketResponseDtoList = new ArrayList<>();
        long totalPrice = 0L;

        for (Basket basket : basketList) {
            basketResponseDtoList.add(
                    new GetBasketResponseDto(
                            basket.getGoods().getName(),
                            basket.getGoods().getPrice(),
                            basket.getAmount()
                    )
            );
            totalPrice += (long) basket.getGoods().getPrice() * basket.getAmount();
        }
        return new GetBasketListResponseDto(basketResponseDtoList, totalPrice);
    }

    @Transactional
    public GetBasketResponseDto editBasket(String email, Long basketId, EditBasketRequestDto requestDto) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_EMAIL.getMessage())
        );
        Basket basket = basketRepository.findById(basketId).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_BASKET.getMessage())
        );
        if (basket.getMember() != member) {
            throw new CustomApiException(ErrorCode.NOT_INCORRECT_MEMBER.getMessage());
        }
        Goods goods = goodsRepository.findById(basket.getGoods().getId()).orElseThrow(() ->
                new CustomApiException(ErrorCode.NOT_FOUND_GOODS.getMessage())
        );

        if (basket.getAmount() < requestDto.amount()) {
            if (goods.getAmount() - (requestDto.amount() - basket.getAmount()) < 0) {
                throw new CustomApiException(ErrorCode.LACK_AMOUNT_GOODS.getMessage());
            }

            goods.updateAmount(goods.getAmount() - (requestDto.amount() - basket.getAmount()));
            basket.updateAmount(requestDto.amount());
        } else {
            goods.updateAmount(goods.getAmount() + basket.getAmount() - requestDto.amount());
            basket.updateAmount(requestDto.amount());
        }
        return new GetBasketResponseDto(goods.getName(), goods.getPrice(), basket.getAmount());
    }
}
