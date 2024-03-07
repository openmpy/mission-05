package com.example.mission05.domain.basket.service;

import com.example.mission05.domain.basket.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BasketService {

    private final BasketRepository basketRepository;
}
