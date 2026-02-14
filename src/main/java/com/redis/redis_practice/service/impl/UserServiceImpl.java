package com.redis.redis_practice.service.impl;

import com.redis.redis_practice.custom_exceptions.UserNotFoundException;
import com.redis.redis_practice.model.User;
import com.redis.redis_practice.repository.UserRepository;
import com.redis.redis_practice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserServiceImpl(){
        logger.info("\n\n\n******************************** Object is Created of UserServiceImpl class *******************************\n\n\n");
    }

    @CachePut(value = "user",key = "#users.id")
    @Override
    public User addUser(User users) {
        return this.userRepository.save(users);
    }

    @Override
    @CachePut(value = "update",key = "#id")
    public User updateUser(long id, User updateUser) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found!"));
        user.setEmail(updateUser.getEmail());
        user.setName(updateUser.getName());
        return this.userRepository.save(user);
    }

    @Cacheable(value = "user",key = "#id")
    @Override
    public User getUserById(Long id) {
        return this.userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User Not Found with this id!!"));
    }

    @Cacheable(value = "users",key = "#root.methodName")
    @Override
    public List<User> allUser() {
        return this.userRepository.findAll();
    }

    @Override
    @CacheEvict(value="delete", key="#id")
    public void deleteUser(long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found!"));
        this.userRepository.deleteById(id);
    }
}
