package com.gzh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.gzh.mapper"})
public class UserPointsStarterApp {
    public static void main(String[] args) {
        SpringApplication.run(UserPointsStarterApp.class,args);
    }
}
