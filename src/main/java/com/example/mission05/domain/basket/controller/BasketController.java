package com.example.mission05.domain.basket.controller;

import com.example.mission05.domain.basket.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/baskets")
@RestController
public class BasketController {

    private final BasketService basketService;
}
