package com.gzh.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "business")
public interface BusinessClient {

    @GetMapping("/notify")
    public void notifyBusiness();
}
