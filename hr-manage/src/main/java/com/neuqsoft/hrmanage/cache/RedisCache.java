//package com.neuqsoft.hrmanage.cache;
//
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.core.RedisTemplate;
//
//@Configuration
//@EnableCaching
//public class RedisCache {
//    @Primary
//    @Bean("CacheManager5m")
//public CacheManager cacheManager5m(){
//        return this.cacheManager()
//    }
//
//    private CacheManager cacheManager(RedisTemplate redisTemplate,long seconds){
//        RedisCacheManager manager=new RedisCacheManager(redisTemplate);
//        manager
//    }
//}
