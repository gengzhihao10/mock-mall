package com.gzh.controller;

import com.gzh.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaceOrferController {

    @Autowired
    private ItemStockClient itemStockClient;
    @Autowired
    private OrderManageClient orderManageClient;
    @Autowired
    private CouponClient couponClient;
    @Autowired
    private UserPointsClient userPointsClient;
    @Autowired
    private BusinessClient businessClient;

    /**
     * @author gengzhihao
     * @date 2024/4/15 10:57
     * @description 模拟用户下单
     * @return String
     */
    @GetMapping("/po")
    public String po(){
        long start = System.currentTimeMillis();

        //1.调用库存服务扣除商品库存
        itemStockClient.decr();
        //2. 调用订单服务，创建订单
        orderManageClient.create();
        //3. 调用优惠券服务，预扣除使用到的优惠券
        couponClient.coupon();
        //4. 调用用户积分服务，预扣除用户使用的积分
        userPointsClient.up();
        //5. 调用商家服务，通知商家用户已下单
        businessClient.notifyBusiness();
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        return "place order success!";
    }
}
