package com.redis.redis_practice.controller;

import com.redis.redis_practice.custom_exceptions.UserNotFoundException;
import com.redis.redis_practice.model.User;
import com.redis.redis_practice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private ApplicationContext context;
   // @Autowired
    public UserController(UserService userService,ApplicationContext context){
        this.userService=userService;
        this.context = context;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user){
        logger.info("adding user with userId,{}",user.getId());
        this.userService.addUser(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/get/by-id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id){
        logger.info("getting user with id {}",id);
        User userById = this.userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(userById);
    }
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllUserById(){
        List<User> users = this.userService.allUser();
        if (users.isEmpty()){
            throw new UserNotFoundException("Users not found!!");
        }
        else {
            logger.info("**********************************************************************");
            String[] beanDefinitionNames = context.getBeanDefinitionNames();
            for (String name : beanDefinitionNames){
                logger.info("bean: {}",name);
            }
            logger.info("total beans{}",beanDefinitionNames.length);
            logger.info("**********************************************************************");

            return ResponseEntity.status(HttpStatus.FOUND).body(users);
        }
    }
    // update api
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") long id,@RequestBody User user){
        User user1 = this.userService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(user1);
    }
    // delete api
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id){
        this.userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}