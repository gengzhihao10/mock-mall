package com.gzh.listener;

import com.gzh.config.RabbitMQConfig;
import com.gzh.service.UserPointsConsumeService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserPointsListener {

    @Autowired
    private UserPointsConsumeService userPointsConsumeService;

    @RabbitListener(queues = {RabbitMQConfig.USER_POINTS_QUEUE})
    public void consume(String msg, Channel channel, Message message) throws InterruptedException, IOException {
        userPointsConsumeService.consume(message);
        // 手动ACK
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

}
