package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class UserCacheRepository {

    private static final String USER_KEY_PREFIX = "USER";

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public void set(User user){
        String key = getKey(user.getUsername());
        redisTemplate.opsForValue().set(key,user,10,TimeUnit.MINUTES);
    }

    public User get(String username){
        String key = getKey(username);
        return (User) redisTemplate.opsForValue().get(key);
    }

    private String getKey(String username) {
        return USER_KEY_PREFIX + username;
    }

}
