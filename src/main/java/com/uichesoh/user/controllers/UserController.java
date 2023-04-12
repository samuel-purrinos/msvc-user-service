package com.uichesoh.user.controllers;

import com.uichesoh.user.entities.User;
import com.uichesoh.user.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<User> saveUser1(@RequestBody User user) {
        User newUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name ="ratingHotelBreaker",fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    public ResponseEntity<User> ratingHotelFallBack(String userId, Exception exception){
    log.info("Inactive service",exception.getMessage());
    User user = User.builder()
            .email("root@gmail.com")
            .name("root")
            .info("This user is created when a service ius down")
            .id("1234")
        .build();
    return ResponseEntity.ok(user);
    }
}
