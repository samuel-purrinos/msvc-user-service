package com.uichesoh.user.service;

import com.uichesoh.user.entities.Hotel;
import com.uichesoh.user.entities.Review;
import com.uichesoh.user.entities.User;
import com.uichesoh.user.exceptions.ResourceNotFoundException;
import com.uichesoh.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;
    @Override
    public User save(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        Review[] userReviews = restTemplate.getForObject("http://localhost:8083/api/v1/reviews/users/"+user.getId(), Review[].class);
        List<Review> reviews = Arrays.stream(userReviews).collect(Collectors.toList());
        List<Review> reviewList = reviews.stream().map(review -> {
            logger.info("Getting hotel with id {}",review.getHotelId());
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8082/api/v1/hotels/"+review.getHotelId(),Hotel.class);
            Hotel hotel = forEntity.getBody();
            logger.info("Hotel Service response with status code : {} ",forEntity.getStatusCode());
            review.setHotel(hotel);
            return review;
        }).collect(Collectors.toList());
        logger.info("{}",userReviews);
        user.setReviews(reviewList);
      return user;
    }
}
