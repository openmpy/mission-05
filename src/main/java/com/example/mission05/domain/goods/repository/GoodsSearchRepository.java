package com.example.mission05.domain.goods.repository;

import com.example.mission05.domain.goods.entity.Goods;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.mission05.domain.goods.entity.QGoods.goods;

@RequiredArgsConstructor
@Repository
public class GoodsSearchRepository {

    private final JPQLQueryFactory jpqlQueryFactory;

    public List<Goods> searchGoods(int page, int size, String sortBy, String orderBy) {
        return jpqlQueryFactory.selectFrom(goods)
                .orderBy(getSortedOrder(sortBy, orderBy))
                .offset((long) page * size)
                .limit(size)
                .fetch();
    }

    private OrderSpecifier<?> getSortedOrder(String sortBy, String orderBy) {
        PathBuilder<Goods> entityPath = new PathBuilder<>(Goods.class, "goods");

        if (orderBy.equalsIgnoreCase("asc")) {
            return entityPath.getString(sortBy).asc();
        } else {
            return entityPath.getString(sortBy).desc();
        }
    }
}
