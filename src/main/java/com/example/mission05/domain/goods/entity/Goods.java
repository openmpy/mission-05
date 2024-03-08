package com.example.mission05.domain.goods.entity;

import com.example.mission05.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "goods_tbl")
public class Goods extends Timestamped {

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

    @Column(name = "image_url")
    private String imageUrl;

    @Builder
    public Goods(String name, Integer price, Integer amount, String introduction, String category, String imageUrl) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.introduction = introduction;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public void updateAmount(int amount) {
        this.amount = amount;
    }

    public void uploadImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
