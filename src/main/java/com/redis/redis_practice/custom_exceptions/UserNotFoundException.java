package com.redis.redis_practice.custom_exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String string){
        super(string);
    }
}
