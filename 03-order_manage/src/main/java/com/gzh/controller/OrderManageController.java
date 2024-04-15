package com.gzh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderManageController {

    @GetMapping("/create")
    public void create() throws InterruptedException {
        Thread.sleep(400);
        System.out.println("扣除创建订单成功！");

    }
}
