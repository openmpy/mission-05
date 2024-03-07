package com.example.mission05.domain.basket.repository;

import com.example.mission05.domain.basket.entity.Basket;
import com.example.mission05.domain.goods.entity.Goods;
import com.example.mission05.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    Optional<Basket> findByMemberAndGoods(Member member, Goods goods);

    List<Basket> findByMemberOrderByCreatedAtDesc(Member member);
}
