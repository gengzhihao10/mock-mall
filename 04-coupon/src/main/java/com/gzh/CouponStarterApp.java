package com.gzh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CouponStarterApp {

    public static void main(String[] args) {
        SpringApplication.run(CouponStarterApp.class,args);
    }
}
