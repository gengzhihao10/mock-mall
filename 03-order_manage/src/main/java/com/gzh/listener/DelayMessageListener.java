package com.gzh.listener;

import com.gzh.config.RabbitMQConfig;
import com.gzh.service.TBOrderService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DelayMessageListener {

    @Autowired
    private TBOrderService tbOrderService;

    @RabbitListener(queues = RabbitMQConfig.ORDER_DEAD_QUEUE)
    public void consume(String id, Channel channel, Message message) throws IOException {
        //1.调用service，实现订单状态处理
        tbOrderService.delayCancelOrder(id);
        //2.ack
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
