package com.example.mission05.domain.goods.controller;

import com.example.mission05.domain.goods.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/goods")
@RestController
public class GoodsController {

    private final GoodsService goodsService;
}
