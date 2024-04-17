package com.gzh.service.impl;


import com.gzh.mapper.UserPointsIdempotentMapper;
import com.gzh.service.UserPointsConsumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserPointsConsumeServiceImpl implements UserPointsConsumeService {


    @Resource
    private UserPointsIdempotentMapper userPointsIdempotentMapper;

    private final String ID_NAME = "spring_returned_message_correlation";

    @Override
    @Transactional
    public void consume(Message message) {
        String id = message.getMessageProperties().getHeader(ID_NAME);
        //1. 查询幂等表，是否存在当前消息标识
        int count = userPointsIdempotentMapper.findById(id);
        //2.如果存在，直接return结束
        if(count == 1){
            log.info("消息 " + id + " 已经被消费，无需重复消费");
            return;
        }
        //3.如果不存在，插入消息标识到幂等表
        userPointsIdempotentMapper.save(id);
        //4.执行消费逻辑

        //预扣除用户积分
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("用户积分预扣除成功！");
    }
}
