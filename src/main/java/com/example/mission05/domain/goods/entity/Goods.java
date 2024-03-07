package com.example.mission05.domain.goods.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "goods_tbl")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "introduction", nullable = false)
    private String introduction;

    @Column(name = "category", nullable = false)
    private String category;

    @Builder
    public Goods(String name, Integer price, Integer amount, String introduction, String category) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.introduction = introduction;
        this.category = category;
    }
}
