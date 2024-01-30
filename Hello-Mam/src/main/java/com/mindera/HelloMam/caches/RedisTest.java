package com.mindera.HelloMam.caches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

@Component
public class RedisTest {
    private final StringRedisTemplate template;

    @Autowired
    public RedisTest(StringRedisTemplate template) {
        this.template = template;
    }

    public void testRedis() {
        // Set a value in Redis
        template.opsForValue().set("testKey", "testValue");

        // Retrieve the value from Redis
        String value = template.opsForValue().get("testKey");

        // Print the value
        System.out.println("Value of 'testKey': " + value);
    }


}
