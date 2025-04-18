package com.redis.redis_practice.service;

import com.redis.redis_practice.model.User;
import org.springframework.stereotype.Service;
import java.util.List;


public interface UserService {
     User addUser(User users);
     User updateUser(long id,User updateUser);
    User getUserById(Long id);
    List<User> allUser();
     void deleteUser(long id);
}
