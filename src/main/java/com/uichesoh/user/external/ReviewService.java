package com.uichesoh.user.external;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("REVIEWS-SERVICE")
public interface ReviewService {

}
