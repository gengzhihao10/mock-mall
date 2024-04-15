package com.gzh.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "order-manage")
public interface OrderManageClient {

    @GetMapping("/create")
    public void create();
}
