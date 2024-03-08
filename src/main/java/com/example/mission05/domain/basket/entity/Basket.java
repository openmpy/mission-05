package com.example.mission05.domain.basket.entity;

import com.example.mission05.domain.goods.entity.Goods;
import com.example.mission05.domain.member.entity.Member;
import com.example.mission05.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "basket_tbl")
public class Basket extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id", nullable = false)
    private Goods goods;

    @Column(name = "amount")
    private int amount;

    @Builder
    public Basket(Member member, Goods goods, int amount) {
        this.member = member;
        this.goods = goods;
        this.amount = amount;
    }

    public void updateAmount(int amount) {
        this.amount = amount;
    }
}
