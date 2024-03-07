package com.example.mission05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    @DisplayName("Redis 연결 테스트")
    void redisTest() {
        // 키-값 쌍을 저장
        redisTemplate.opsForValue().set("key", "value");

        // 같은 키로 값을 검색
        String value = redisTemplate.opsForValue().get("key");

        // 저장한 값과 검색한 값이 같은지 확인
        assertEquals("value", value);
    }
}
