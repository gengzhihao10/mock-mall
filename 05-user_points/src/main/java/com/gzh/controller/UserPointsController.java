package com.gzh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPointsController {

    @GetMapping("/up")
    public void up() throws InterruptedException {
        Thread.sleep(400);
        System.out.println("扣除用户积分成功！！");
    }

}
