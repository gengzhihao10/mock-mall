package com.gzh.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "item-stock")
public interface ItemStockClient {

    @GetMapping("/decr")
    public void decr();
}
