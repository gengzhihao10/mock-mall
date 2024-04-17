package com.gzh.controller;

import com.gzh.service.TBOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderManageController {

    @Autowired
    private TBOrderService tbOrderService;

    @GetMapping("/create")
    public void create() throws InterruptedException {
        tbOrderService.save();
        log.info("创建订单成功");
    }
}
