package com.gzh.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "user-points")
public interface UserPointsClient {

    @GetMapping("/up")
    public void up();
}
