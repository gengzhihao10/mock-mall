package com.gzh.service.impl;

import com.gzh.config.RabbitMQConfig;
import com.gzh.mapper.TBOrderMapper;
import com.gzh.service.TBOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Slf4j
@Service
public class TBOrderServiceImpl implements TBOrderService {

    @Resource
    private TBOrderMapper tbOrderMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    @Override
    public void save() {
        String id = UUID.randomUUID().toString();
        tbOrderMapper.save(id);
        //订单创建成功，将消息发送到rabbitmq的普通交换机
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_EXCHANGE, "", id, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //设置消息的生存时间为15秒。也可以在构建队列时指定队列的生存时间
                message.getMessageProperties().setExpiration("15000");
                return message;
            }
        });
    }

    @Override
    @Transactional
    public void delayCancelOrder(String id) {
        //1.基于id查询订单信息，for update
        int orderState = tbOrderMapper.findOrderStateByIdForUpdate(id);
        //2. 判断订单状态
        if (orderState != 0){
            log.info("订单已经支付");
            return;
        }
        //3.修改订单状态
        tbOrderMapper.updateOrderStateById(-1,id);
        log.info("订单未支付，修改订单状态为已取消");
    }

}
